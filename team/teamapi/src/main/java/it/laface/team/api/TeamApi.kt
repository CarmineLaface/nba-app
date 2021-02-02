package it.laface.team.api

import com.google.gson.Gson
import it.laface.networking.getApiService
import it.laface.networking.getClient
import it.laface.networking.getConverter
import it.laface.networking.getGson
import it.laface.team.api.roster.TeamRosterService
import it.laface.team.api.teaminfo.TeamDetailsDeserializer
import it.laface.team.api.teaminfo.TeamDetailsResponse
import it.laface.team.api.teaminfo.TeamDetailsService

object TeamApi {

    val teamRosterGson: Gson
        get() = getGson(BuildConfig.NBA_API_DATE_FORMAT)

    val teamRosterService: TeamRosterService by lazy {
        val client = getClient()
        val converter = getConverter(teamRosterGson)
        getApiService(
            baseUrl = BuildConfig.NBA_API_BASE_URL,
            converterFactory = converter,
            client = client
        )
    }

    val teamDetailsGson: Gson
        get() = getGson(adapterInfo = TeamDetailsResponse::class.java to TeamDetailsDeserializer)

    val teamDetailsService: TeamDetailsService by lazy {
        val client = getClient()
        val converter = getConverter(teamDetailsGson)
        getApiService(
            baseUrl = TeamDetailsService.BASE_URL,
            converterFactory = converter,
            client = client
        )
    }
}
