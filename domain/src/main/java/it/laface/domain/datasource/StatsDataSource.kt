package it.laface.domain.datasource

import it.laface.domain.NetworkResult
import it.laface.domain.model.StatsGroup

interface StatsDataSource {

    suspend fun getLeaders(): NetworkResult<List<StatsGroup>>
}
