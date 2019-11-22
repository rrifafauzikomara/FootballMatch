package com.rifafauzi.footballmatch.common

/**
 * Created by rrifafauzikomara on 2019-11-22.
 */

sealed class Result<out T> {
    class NoInternetConnection<out T> : Result<T>()
    class Loading<out T> : Result<T>()
    class NoData<out T> : Result<T>()
    data class HasData<out T>(val data : T) : Result<T>()
    data class ErrorInformation<out T> (val message : String) : Result<T>()
}