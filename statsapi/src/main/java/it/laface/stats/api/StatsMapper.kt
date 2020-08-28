package it.laface.stats.api

import it.laface.base.NetworkResult
import it.laface.base.NetworkResult.Success
import it.laface.networking.toNetworkResult
import it.laface.stats.domain.Leader
import it.laface.stats.domain.StatsDataSource
import it.laface.stats.domain.StatsSection

class StatsMapper(private val service: StatsService) : StatsDataSource {

    override suspend fun getLeaders(): NetworkResult<List<StatsSection>> {
        val playerStats = service.getPlayersStats().toNetworkResult { response ->
            response.sections.map(::toDomain)
        }
        val advancedLeadersStats = service.getAdvancedLeadersStats().toNetworkResult { response ->
            response.sections.subList(0, MAX_INDEX_FOR_GROUP).map(::toDomain)
        }
        return if (playerStats is Success && advancedLeadersStats is Success) {
            Success(playerStats.value + advancedLeadersStats.value)
        } else playerStats
    }

    private fun toDomain(index: Int, player: PlayerResponse): Leader =
        Leader(
            playerName = player.playerName,
            playerId = player.playerId.toString(),
            position = index + 1,
            teamId = player.teamId.toString(),
            customValue = player.value.parseCustomValue()
        )

    private fun toDomain(group: StatsSectionResponse): StatsSection =
        StatsSection(
            title = group.title,
            players = group.players.mapIndexed(::toDomain)
        )

    @Suppress("MagicNumber")
    private fun String.parseCustomValue(): String {
        if (contains('.').not()) return this
        val pointIndex = indexOf('.')
        return if (startsWith("0.")) {
            val endIndex = pointIndex + 4
            safeSubstring(1, endIndex)
        } else {
            safeSubstring(0, pointIndex + 3)
        }
    }

    private fun String.safeSubstring(startIndex: Int, endIndex: Int): String =
        if (endIndex > length)
            substring(startIndex)
        else
            substring(startIndex, endIndex)

    companion object {

        private const val MAX_INDEX_FOR_GROUP = 8
    }
}
