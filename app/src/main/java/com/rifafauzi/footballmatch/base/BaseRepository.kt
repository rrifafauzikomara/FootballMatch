package com.rifafauzi.footballmatch.base

import com.rifafauzi.footballmatch.vo.Result
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception

/**
 * Created by rrifafauzikomara on 2019-10-29.
 */
 
open class BaseRepository {

    suspend fun <T> getApiResult(call: suspend () -> Response<T>) : Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()

                if (body != null) {
                    return Result.success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {
        Timber.e(message)
        return Result.error("Network call has failed for a following reason : $message", null)
    }
}