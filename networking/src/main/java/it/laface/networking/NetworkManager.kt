package it.laface.networking

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

inline fun <reified T> getApiService(
    baseUrl: String = ApiHelper.BASE_URL,
    converterFactory: Converter.Factory,
    client: OkHttpClient.Builder = baseClient
): T {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(converterFactory)
        .client(client.build())
        .build()
        .create(T::class.java)
}

val baseClient: OkHttpClient.Builder by lazy {
    OkHttpClient.Builder()
        .fastFallback(true)
        .addInterceptor(IOExceptionInterceptor())
}

fun getGson(
    dateFormat: String? = null,
    adapterInfo: AdapterInfo? = null
): Gson =
    GsonBuilder()
        .apply {
            if (dateFormat != null) {
                setDateFormat(dateFormat)
            }
            adapterInfo?.let { (type, typeAdapter) ->
                registerTypeAdapter(type, typeAdapter)
            }
        }
        .create()

fun getConverterFactory(gson: Gson): Converter.Factory =
    GsonConverterFactory(gson)

typealias AdapterInfo = Pair<Type, Any>
