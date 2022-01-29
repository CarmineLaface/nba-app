package it.laface.game.networking

import com.google.gson.Gson
import it.laface.networking.ApiHelper
import it.laface.networking.getApiService
import it.laface.networking.getClient
import it.laface.networking.getConverterFactory
import it.laface.networking.getGson

object GameApi {

    val gson: Gson
        get() = getGson(ApiHelper.DATE_FORMAT)

    val service: GameService by lazy {
        val client = getClient()
        val converter = getConverterFactory(gson)
        getApiService(
            baseUrl = ApiHelper.BASE_URL,
            converterFactory = converter,
            client = client
        )
    }
}
