package it.laface.ranking.domain

import it.laface.base.NetworkResult

interface RankingDataSource {

    suspend fun getRanking(): NetworkResult<RankingLists>
}
