package it.laface.team.api

import com.google.gson.Gson
import it.laface.networking.ApiHelper
import it.laface.networking.getApiService
import it.laface.networking.getConverterFactory
import it.laface.networking.getGson
import it.laface.team.api.roster.TeamRosterService
import it.laface.team.api.teaminfo.TeamDetailsDeserializer
import it.laface.team.api.teaminfo.TeamDetailsResponse
import it.laface.team.api.teaminfo.TeamDetailsService

object TeamApi {

    val teamRosterGson: Gson
        get() = getGson(ApiHelper.DATE_FORMAT)

    val teamRosterService: TeamRosterService by lazy {
        val converter = getConverterFactory(teamRosterGson)
        getApiService(
            converterFactory = converter,
        )
    }

    val teamDetailsGson: Gson
        get() = getGson(adapterInfo = TeamDetailsResponse::class.java to TeamDetailsDeserializer)

    val teamDetailsService: TeamDetailsService by lazy {
        val converter = getConverterFactory(teamDetailsGson)
        getApiService(
            baseUrl = TeamDetailsService.BASE_URL,
            converterFactory = converter,
        )
    }
}
