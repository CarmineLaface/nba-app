package it.laface.ranking.networking

import retrofit2.Response
import retrofit2.http.GET

interface RankingService {

    @GET("/prod/v1/current/standings_conference.json")
    suspend fun ranking(): Response<RankingResponse>
}
