package it.laface.domain.datasource

import it.laface.domain.model.RankingLists
import it.laface.domain.network.NetworkResult

interface RankingDataSource {

    suspend fun getRanking(): NetworkResult<RankingLists>
}
