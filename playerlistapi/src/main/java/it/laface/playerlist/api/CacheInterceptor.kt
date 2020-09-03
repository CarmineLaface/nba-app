package it.laface.playerlist.api

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

class CacheInterceptor : Interceptor {

    override fun intercept(chain: Chain): Response {
        return chain.proceed(chain.request())
            .newBuilder()
            .header("Cache-Control", "public, max-age=$CACHE_TIME_IN_SECONDS")
            .build()
    }

    companion object {
        private const val CACHE_TIME_IN_SECONDS: Int = 60 * 60 * 24 * 7
    }
}
