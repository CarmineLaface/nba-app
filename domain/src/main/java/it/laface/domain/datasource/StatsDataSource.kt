package it.laface.domain.datasource

import it.laface.domain.model.StatsSection
import it.laface.domain.network.NetworkResult

interface StatsDataSource {

    suspend fun getLeaders(): NetworkResult<List<StatsSection>>
}
