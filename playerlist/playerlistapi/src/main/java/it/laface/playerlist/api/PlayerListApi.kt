package it.laface.playerlist.api

import it.laface.networking.getApiService
import it.laface.networking.getClient
import it.laface.networking.getConverter
import okhttp3.Cache
import java.io.File

object PlayerListApi {

    private var service: PlayerListService? = null

    fun getService(cacheDirPath: String): PlayerListService =
        service ?: initService(File(cacheDirPath))

    private fun initService(cacheDirectory: File): PlayerListService {
        val cache = Cache(cacheDirectory, CACHE_SIZE_IN_BYTE)
        val client = getClient(CacheInterceptor()).cache(cache)
        val converter = getConverter(BuildConfig.NBA_API_DATE_FORMAT)
        service = getApiService(
            baseUrl = BuildConfig.NBA_API_BASE_URL,
            converterFactory = converter,
            client = client
        )
        return service!!
    }

    private const val CACHE_SIZE_IN_BYTE: Long = 1024 * 1024
}
