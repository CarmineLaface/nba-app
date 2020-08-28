package it.laface.team.api

import it.laface.networking.getApiService
import it.laface.networking.getClient
import it.laface.networking.getConverter

object TeamRosterApi {

    val service: TeamRosterService by lazy {
        val client = getClient()
        val converter = getConverter()
        getApiService(converterFactory = converter, client = client)
    }
}
