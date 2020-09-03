package it.laface.stats.api

import it.laface.networking.getApiService
import it.laface.networking.getClient
import it.laface.networking.getConverter

object StatsApi {

    val service: StatsService by lazy {
        val client = getClient()
        val converter = getConverter(
            dateFormat = StatsService.DATE_FORMAT,
            adapterInfo = PlayerStatsResponse::class.java to StatsDeserializer
        )
        getApiService(StatsService.BASE_URL, converter, client)
    }
}
