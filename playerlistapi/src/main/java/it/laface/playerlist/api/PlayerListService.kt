package it.laface.playerlist.api

import it.laface.networking.ApiHelper.nbaSeason
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlayerListService {

    @GET("/prod/v1/{year}/players.json")
    suspend fun playerList(@Path(value = "year") year: String = nbaSeason):
        Response<PlayerListResponse>
}
