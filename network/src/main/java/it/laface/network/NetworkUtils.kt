package it.laface.network

import it.laface.domain.NetworkError
import it.laface.domain.NetworkResult
import it.laface.network.IOExceptionInterceptor.Companion.GENERIC_EXCEPTION_STATUS_CODE
import it.laface.network.IOExceptionInterceptor.Companion.MISSING_CONNECTION_STATUS_CODE
import it.laface.network.IOExceptionInterceptor.Companion.TIMEOUT_STATUS_CODE
import retrofit2.Response
import java.net.HttpURLConnection.HTTP_BAD_REQUEST
import java.net.HttpURLConnection.HTTP_INTERNAL_ERROR
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED

fun <T, R> Response<T>.toNetworkResult(block: (T) -> R): NetworkResult<R> {
    val body = body()
    return if (isSuccessful && body != null) {
        NetworkResult.Success(block(body))
    } else {
        NetworkResult.Error(mapError())
    }
}

fun <T> Response<T>.mapError(): NetworkError {
    return when (code()) {
        MISSING_CONNECTION_STATUS_CODE -> NetworkError.MissingConnection
        TIMEOUT_STATUS_CODE -> NetworkError.Timeout
        GENERIC_EXCEPTION_STATUS_CODE -> NetworkError.UnknownError(errorMessage)
        HTTP_UNAUTHORIZED -> NetworkError.Unauthorized
        in HTTP_BAD_REQUEST until HTTP_INTERNAL_ERROR -> NetworkError.ClientError(errorMessage, code())
        else -> NetworkError.ServerError(errorMessage, code())
    }
}

val <T> Response<T>.errorMessage: String
    get() = errorBody()?.string() ?: message()
