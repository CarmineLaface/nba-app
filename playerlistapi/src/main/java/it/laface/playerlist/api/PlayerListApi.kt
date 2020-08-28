package it.laface.playerlist.api

import it.laface.networking.getApiService
import it.laface.networking.getClient
import it.laface.networking.getConverter

object PlayerListApi {

    val service: PlayerListService by lazy {
        val client = getClient()
        val converter = getConverter()
        getApiService(converterFactory = converter, client = client)
    }
}
