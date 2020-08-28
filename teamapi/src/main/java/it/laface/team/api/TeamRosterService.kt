package it.laface.team.api

import it.laface.networking.ApiHelper.nbaSeason
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TeamRosterService {

    @GET("/v2015/json/mobile_teams/nba/{year}/teams/{teamSlug}_roster.json")
    suspend fun teamRoster(
        @Path(value = "teamSlug") teamSlug: String,
        @Path(value = "year") year: String = nbaSeason
    ): Response<TeamRosterResponse>
}
