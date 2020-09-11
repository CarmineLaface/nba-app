package it.laface.team.api.teaminfo

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TeamDetailsService {

    @GET("/feeds/teams/profile/{teamId}_TeamProfile.js")
    suspend fun getTeamDetails(
        @Path(value = "teamId") teamId: String
    ): Response<TeamDetailsResponse>

    companion object {
        internal const val BASE_URL: String = "https://stats.nba.com/"
    }
}
