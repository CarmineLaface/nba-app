package it.laface.domain

inline fun <T, R> NetworkResult<T>.flatMapSuccess(block: (T) -> NetworkResult<R>): NetworkResult<R> {
    return when (this) {
        is NetworkResult.Success -> block(value)
        is NetworkResult.Error -> this
    }
}
