package it.laface.stats.api

import it.laface.base.NetworkResult
import it.laface.base.NetworkResult.Success
import it.laface.networking.toNetworkResult
import it.laface.stats.domain.StatsDataSource
import it.laface.stats.domain.StatsSection

class StatsMapper(private val service: StatsService) : StatsDataSource {

    override suspend fun getLeaders(): NetworkResult<List<StatsSection>> {
        val playerStats = service.getPlayersStats().toNetworkResult { response ->
            response.sections
        }
        val advancedLeadersStats = service.getAdvancedLeadersStats().toNetworkResult { response ->
            response.sections.subList(0, MAX_INDEX_FOR_GROUP)
        }
        return when {
            playerStats is Success && advancedLeadersStats is Success -> {
                Success(playerStats.value + advancedLeadersStats.value)
            }
            playerStats is Success -> playerStats
            advancedLeadersStats is Success -> advancedLeadersStats
            else -> playerStats
        }
    }

    companion object {

        private const val MAX_INDEX_FOR_GROUP = 8
    }
}
