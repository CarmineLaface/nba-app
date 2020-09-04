package it.laface.player.api

import it.laface.networking.ApiHelper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlayerStatsService {

    @GET("/prod/v1/{year}/players/{id}_profile.json")
    suspend fun playerStats(
        @Path(value = "year") year: String = ApiHelper.nbaSeason,
        @Path(value = "id") playerId: String,
    ): Response<PlayerStatsResponse>
}
