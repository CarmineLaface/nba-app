package it.laface.stats.api

import retrofit2.Response
import retrofit2.http.GET

interface StatsService {

    @GET("/js/data/widgets/players_landing_inner.json")
    suspend fun getPlayersStats(): Response<PlayerStatsResponse>

    @GET("/js/data/widgets/advanced_leaders.json")
    suspend fun getAdvancedLeadersStats(): Response<PlayerStatsResponse>

    companion object {

        const val BASE_URL: String = "https://stats.nba.com"
        const val DATE_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ssXXX"
    }
}