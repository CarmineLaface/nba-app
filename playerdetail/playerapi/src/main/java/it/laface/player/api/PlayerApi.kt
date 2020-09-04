package it.laface.player.api

import it.laface.networking.getApiService
import it.laface.networking.getClient
import it.laface.networking.getConverter

object PlayerApi {

    val service: PlayerStatsService by lazy {
        val client = getClient()
        val converter = getConverter(BuildConfig.NBA_API_DATE_FORMAT)
        getApiService(
            baseUrl = BuildConfig.NBA_API_BASE_URL,
            converterFactory = converter,
            client = client
        )
    }
}
