package it.laface.domain.datasource

import it.laface.domain.network.NetworkResult
import it.laface.domain.model.RankingLists

interface RankingDataSource {

    suspend fun getRanking(): NetworkResult<RankingLists>
}
