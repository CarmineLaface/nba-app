package it.laface.game.networking

import com.google.gson.Gson
import it.laface.networking.ApiHelper
import it.laface.networking.getApiService
import it.laface.networking.getConverterFactory
import it.laface.networking.getGson

object GameApi {

    val gson: Gson
        get() = getGson(ApiHelper.DATE_FORMAT)

    val service: GameService by lazy {
        val converter = getConverterFactory(gson)
        getApiService(
            converterFactory = converter,
        )
    }
}
