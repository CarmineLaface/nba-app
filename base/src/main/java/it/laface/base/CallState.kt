package it.laface.base

sealed interface CallState<out T> {

    object NotStarted : CallState<Nothing>

    object InProgress : CallState<Nothing>

    data class Success<out T>(val result: T) : CallState<T>

    data class Error(val error: NetworkError) : CallState<Nothing>
}
