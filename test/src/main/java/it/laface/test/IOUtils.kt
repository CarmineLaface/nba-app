package it.laface.test

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import java.net.HttpURLConnection.HTTP_INTERNAL_ERROR

fun <T> successfulResponse(contentBody: T): Response<T> =
    Response.success(contentBody)

fun <T> errorResponse(httpCode: Int = HTTP_INTERNAL_ERROR, jsonErrorBody: String = "error"): Response<T> =
    Response.error(
        httpCode,
        jsonErrorBody.toResponseBody("application/json".toMediaType())
    )
