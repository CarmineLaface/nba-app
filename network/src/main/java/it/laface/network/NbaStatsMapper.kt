package it.laface.network

import it.laface.api.NbaStats
import it.laface.domain.network.NetworkResult
import it.laface.domain.network.NetworkResult.Success
import it.laface.domain.datasource.StatsDataSource
import it.laface.domain.model.StatsSection

class NbaStatsMapper(private val api: NbaStats) : StatsDataSource {

    @Suppress("MagicNumber")
    override suspend fun getLeaders(): NetworkResult<List<StatsSection>> {
        val playerStats = api.getPlayersStats().toNetworkResult { response ->
            response.sections.map(::toDomain)
        }
        val advancedLeadersStats = api.getAdvancedLeadersStats().toNetworkResult { response ->
            response.sections.subList(0, 8).map(::toDomain)
        }
        return if (playerStats is Success && advancedLeadersStats is Success) {
            Success(playerStats.value + advancedLeadersStats.value)
        } else playerStats
    }
}
