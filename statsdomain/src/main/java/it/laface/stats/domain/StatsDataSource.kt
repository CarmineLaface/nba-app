package it.laface.stats.domain

import it.laface.base.NetworkResult

interface StatsDataSource {

    suspend fun getLeaders(): NetworkResult<List<StatsSection>>
}