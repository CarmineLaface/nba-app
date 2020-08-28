package it.laface.schedule.api

import it.laface.networking.ApiHelper.nbaSeason
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ScheduleService {

    @GET("/prod/v2/{year}/schedule.json")
    suspend fun leagueSchedule(@Path(value = "year") year: String = nbaSeason):
            Response<ScheduleResponse>

    @GET("/prod/v1/{year}/teams/{teamId}/schedule.json")
    suspend fun teamSchedule(
        @Path(value = "teamId") teamId: String,
        @Path(value = "year") year: String = nbaSeason
    ): Response<ScheduleResponse>
}