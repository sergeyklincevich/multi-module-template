package com.klinserg.news.data

sealed class RequestResult<out T : Any?> {
    data object InProgress : RequestResult<Nothing>()
    class Error(val message: String?) : RequestResult<Nothing>()
    class Success<out T : Any?>(val data: T) : RequestResult<T>()
}

internal fun <I, O> RequestResult<I>.map(mapper: (I) -> O): RequestResult<O> {
    return when (this) {
        is RequestResult.InProgress -> RequestResult.InProgress
        is RequestResult.Success -> RequestResult.Success(mapper(data))
        is RequestResult.Error -> RequestResult.Error(message)
    }

}

internal fun <T : Any> Result<T>.toRequestResult(): RequestResult<T> {
    return when {
        isSuccess -> RequestResult.Success(data = getOrThrow())
        isFailure -> RequestResult.Error(message = exceptionOrNull()?.message)
        else -> error("Not possible branch of Result")
    }

}