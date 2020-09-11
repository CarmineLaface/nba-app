package it.laface.team.api

import it.laface.networking.getApiService
import it.laface.networking.getClient
import it.laface.networking.getConverter
import it.laface.team.api.roster.TeamRosterService
import it.laface.team.api.teaminfo.TeamDetailsDeserializer
import it.laface.team.api.teaminfo.TeamDetailsResponse
import it.laface.team.api.teaminfo.TeamDetailsService

object TeamApi {

    val teamRosterService: TeamRosterService by lazy {
        val client = getClient()
        val converter = getConverter(BuildConfig.NBA_API_DATE_FORMAT)
        getApiService(
            baseUrl = BuildConfig.NBA_API_BASE_URL,
            converterFactory = converter,
            client = client
        )
    }

    val teamDetailsService: TeamDetailsService by lazy {
        val client = getClient()
        val converter = getConverter(
            adapterInfo = TeamDetailsResponse::class.java to TeamDetailsDeserializer
        )
        getApiService(
            baseUrl = TeamDetailsService.BASE_URL,
            converterFactory = converter,
            client = client
        )
    }
}
