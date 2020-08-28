package it.laface.schedule.api

import it.laface.networking.getApiService
import it.laface.networking.getClient
import it.laface.networking.getConverter

object ScheduleApi {

    val service: ScheduleService by lazy {
        val client = getClient()
        val converter = getConverter()
        getApiService(converterFactory = converter, client = client)
    }
}
