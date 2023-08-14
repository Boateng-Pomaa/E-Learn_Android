package com.example.e_learn.utils

//data class Resource<T>( val data: T?, val message: String?){
//        fun isSuccess(): Boolean = data != null
//
//        fun isError(): Boolean = message != null
//
//        fun isLoading():Boolean = data == null && message == null
//    }

sealed class Resource<out T>(
    val data: T? = null,
    val errorMessage: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T> : Resource<T>()
    class Error<T>(errorMessage: String) : Resource<T>(errorMessage = errorMessage)

    fun isSuccess(): Boolean = this is Success && data != null
    fun isLoading(): Boolean = this is Loading
    fun isError(): Boolean = this is Error

    companion object {
        fun <T> success(data: T): Resource<T> = Success(data)
        fun <T> loading(): Resource<T> = Loading()
        fun <T> error(errorMessage: String): Resource<T> = Error(errorMessage)
    }
}


