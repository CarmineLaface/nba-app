package it.laface.stats.api

import com.google.gson.Gson
import it.laface.networking.getApiService
import it.laface.networking.getConverterFactory
import it.laface.networking.getGson

object StatsApi {

    val gson: Gson
        get() = getGson(
            adapterInfo = PlayerStatsResponse::class.java to StatsDeserializer
        )

    val service: StatsService by lazy {
        val converter = getConverterFactory(gson)
        getApiService(StatsService.BASE_URL, converter)
    }
}
