package it.laface.api

import it.laface.api.ApiHelper.nbaSeason
import it.laface.api.models.PlayerListResponse
import it.laface.api.models.RankingResponse
import it.laface.api.models.ScheduleResponse
import it.laface.api.models.TeamRosterResponse
import it.laface.api.models.TeamScheduleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NbaServices {

    @GET("/prod/v1/{year}/players.json")
    suspend fun playerList(@Path(value = "year") year: String = nbaSeason):
        Response<PlayerListResponse>

    @GET("/prod/v1/current/standings_conference.json")
    suspend fun ranking(): Response<RankingResponse>

    @GET("/prod/v2/{year}/schedule.json")
    suspend fun leagueSchedule(@Path(value = "year") year: String = nbaSeason):
        Response<ScheduleResponse>

    @GET("/v2015/json/mobile_teams/nba/{year}/teams/{teamSlug}_roster.json")
    suspend fun teamRoster(
        @Path(value = "teamSlug") teamSlug: String,
        @Path(value = "year") year: String = nbaSeason
    ): Response<TeamRosterResponse>

    @GET("/prod/v1/2018/teams/{teamId}/schedule.json")
    suspend fun teamSchedule(@Path(value = "teamId") teamId: String):
        Response<TeamScheduleResponse>

    /*
    @GET("/prod/v1/2018/players/{playerId}_profile.json")
    fun playerProfile(@Path(value = "playerId", encoded = true) playerId: String)
            : Response<PlayerProfileRes>
            */

    companion object {

        const val BASE_URL: String = "https://data.nba.net"
        const val DATE_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ss.Z'Z'"
    }
}
