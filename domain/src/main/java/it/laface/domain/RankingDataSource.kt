package it.laface.domain

interface RankingDataSource {

    suspend fun getRanking(): NetworkResult<RankingLists>
}
