package it.laface.networking

import it.laface.base.NetworkError
import it.laface.base.NetworkResult
import it.laface.networking.IOExceptionInterceptor.Companion.GENERIC_EXCEPTION_STATUS_CODE
import it.laface.networking.IOExceptionInterceptor.Companion.MISSING_CONNECTION_STATUS_CODE
import it.laface.networking.IOExceptionInterceptor.Companion.TIMEOUT_STATUS_CODE
import retrofit2.Response
import java.net.HttpURLConnection.HTTP_BAD_REQUEST
import java.net.HttpURLConnection.HTTP_INTERNAL_ERROR

inline fun <T, R> Response<T>.toNetworkResult(block: (T) -> R): NetworkResult<R> {
    val body = body()
    return if (isSuccessful && body != null) {
        NetworkResult.Success(block(body))
    } else {
        NetworkResult.Failure(mapError())
    }
}

fun <T> Response<T>.mapError(): NetworkError =
    when (code()) {
        MISSING_CONNECTION_STATUS_CODE -> NetworkError.MissingConnection
        TIMEOUT_STATUS_CODE -> NetworkError.Timeout
        GENERIC_EXCEPTION_STATUS_CODE -> NetworkError.UnknownError(errorMessage)
        in HTTP_BAD_REQUEST until HTTP_INTERNAL_ERROR -> NetworkError.ClientError(errorMessage, code())
        else -> NetworkError.ServerError(errorMessage, code())
    }

val <T> Response<T>.errorMessage: String
    get() = errorBody()?.string() ?: message()
