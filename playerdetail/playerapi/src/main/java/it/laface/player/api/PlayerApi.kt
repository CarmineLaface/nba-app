package it.laface.player.api

import com.google.gson.Gson
import it.laface.networking.ApiHelper
import it.laface.networking.getApiService
import it.laface.networking.getClient
import it.laface.networking.getConverterFactory
import it.laface.networking.getGson

object PlayerApi {

    val gson: Gson
        get() = getGson(ApiHelper.DATE_FORMAT)

    val service: PlayerStatsService by lazy {
        val client = getClient()
        val converter = getConverterFactory(gson)
        getApiService(
            baseUrl = ApiHelper.BASE_URL,
            converterFactory = converter,
            client = client
        )
    }
}
