package it.laface.game.networking

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GameService {

    @GET("/prod/v1/{gameDate}/{gameId}_boxscore.json")
    suspend fun gameBoxScore(
        @Path(value = "gameDate") gameDate: String,
        @Path(value = "gameId") gameId: String,
    ): Response<GameResponse>
}
