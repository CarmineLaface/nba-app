package it.laface.test

import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import java.net.HttpURLConnection.HTTP_INTERNAL_ERROR

inline fun <reified T> successfulResponse(gson: Gson = Gson(), content: String): Response<T> {
    val response = gson.fromJson(content, T::class.java)
    return Response.success(response)
}

fun <T> errorResponse(
    httpCode: Int = HTTP_INTERNAL_ERROR,
    jsonErrorBody: String = "error"
): Response<T> {
    val responseBody = jsonErrorBody.toResponseBody("application/json".toMediaType())
    return Response.error(httpCode, responseBody)
}
