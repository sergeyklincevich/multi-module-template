package com.klinserg.news.data

interface MergeStrategy<T> {
    fun merge(right: T, left: T): T
}

class RequestResultMergeStrategy<T : Any> : MergeStrategy<RequestResult<T>> {

    override fun merge(cache: RequestResult<T>, server: RequestResult<T>): RequestResult<T> {
        return when {
            cache is RequestResult.InProgress && server is RequestResult.InProgress ->
                merge(cache, server)

            cache is RequestResult.InProgress && server is RequestResult.Error ->
                merge(cache, server)

            cache is RequestResult.InProgress && server is RequestResult.Success ->
                merge(cache, server)

            cache is RequestResult.Success && server is RequestResult.InProgress ->
                merge(cache, server)

            cache is RequestResult.Success && server is RequestResult.Error ->
                merge(cache, server)

            cache is RequestResult.Success && server is RequestResult.Success ->
                merge(cache, server)

            else -> error("Not implemented branch of Merge Strategy: Cache: $cache and Server: $server")
        }
    }

    private fun merge(
        cache: RequestResult.InProgress,
        server: RequestResult.InProgress,
    ): RequestResult<T> {
        return server
    }

    private fun merge(
        cache: RequestResult.InProgress,
        server: RequestResult.Error,
    ): RequestResult<T> {
        return cache
    }

    private fun merge(
        cache: RequestResult.Success<T>,
        server: RequestResult.InProgress,
    ): RequestResult<T> {
        return cache
    }

    private fun merge(
        cache: RequestResult.InProgress,
        server: RequestResult.Success<T>,
    ): RequestResult<T> {
        return server
    }

    private fun merge(
        cache: RequestResult.Success<T>,
        server: RequestResult.Error,
    ): RequestResult<T> {
        return cache
    }

    private fun merge(
        cache: RequestResult.Success<T>,
        server: RequestResult.Success<T>,
    ): RequestResult<T> {
        return server
    }
}

