package it.laface.network

import it.laface.api.NbaStats
import it.laface.domain.NetworkResult
import it.laface.domain.NetworkResult.Success
import it.laface.domain.datasource.StatsDataSource
import it.laface.domain.model.StatsGroup

class NbaStatsMapper(private val api: NbaStats) : StatsDataSource {

    override suspend fun getLeaders(): NetworkResult<List<StatsGroup>> {
        val playerStats = api.getPlayersStats().toNetworkResult { response ->
            response.sections.map(::toDomain)
        }
        val advancedLeadersStats = api.getAdvancedLeadersStats().toNetworkResult { response ->
            response.sections.map(::toDomain)
        }
        return if (playerStats is Success && advancedLeadersStats is Success) {
            Success(playerStats.value + advancedLeadersStats.value)
        } else playerStats
    }
}
