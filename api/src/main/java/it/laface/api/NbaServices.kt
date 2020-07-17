package it.laface.api

import it.laface.api.ApiHelper.nbaSeason
import it.laface.api.models.PlayerListResponse
import it.laface.api.models.RankingResponse
import it.laface.api.models.ScheduleResponse
import it.laface.api.models.TeamConfigurationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NbaServices {

    @GET("/prod/{year}/teams_config.json")
    suspend fun teamConfig(@Path(value = "year") year: String = nbaSeason):
        Response<TeamConfigurationResponse>

    @GET("/prod/v1/{year}/players.json")
    suspend fun playerList(@Path(value = "year") year: String = nbaSeason):
        Response<PlayerListResponse>

    @GET("/prod/v1/current/standings_conference.json")
    suspend fun ranking(): Response<RankingResponse>

    @GET("/prod/v2/{year}/schedule.json")
    suspend fun leagueSchedule(@Path(value = "year") year: String = nbaSeason):
        Response<ScheduleResponse>

    /*@GET("/v2015/json/mobile_teams/nba/2018/teams/{teamSlug}_roster.json")
    suspend fun teamRoster(@Path(value = "teamSlug", encoded = true) teamSlug: String)
            : Response<TeamRosterRes>

    @GET("/prod/v1/2018/teams/{teamId}/schedule.json")
    suspend fun teamSchedule(@Path(value = "teamId", encoded = true) teamId: String)
            : Response<ScheduleRes>*/

    /*

    @GET("/v2015/json/mobile_teams/nba/2018/players/playercard_{playerId}_02.json")
    suspend fun playerCard(@Path(value = "playerId", encoded = true) playerId: String)
            : Response<PlayerCardRes>*/

    //region UNUSED
    /*
    @GET("/json/ge/{teamSlug}/default.json")
    fun teamBackgroundImages(@Path(value = "teamSlug", encoded = true) teamSlug: String)
            : Response<TeamDefaultRes>

    @GET("/prod/v1/2018/players/{playerId}_profile.json")
    fun playerProfile(@Path(value = "playerId", encoded = true) playerId: String)
            : Response<PlayerProfileRes>

    @GET("/prod/v1/2018/teams.json")
    fun teamList(): Response<TeamListRes>


    @GET("/json/ge/brands.json")
    fun teamBrand(): Response<List<NbaTeam>>


    @GET("/json/bios/player_{playerId}.json")
    fun playerDetail(@Path(value = "playerId", encoded = true) playerId: String)
            : Response<PlayerInfoRes>
            */
    //endregion

    companion object {

        const val BASE_URL: String = "https://data.nba.net"
        const val DATE_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ss.Z'Z'"
    }
}
