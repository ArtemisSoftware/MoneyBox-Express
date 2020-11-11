package com.example.minimoneybox.utils

class Resource<T>(val status: Status, val data : T? = null, val message : String) {



    enum class Status {
        SUCCESS, ERROR, LOADING
    }


    companion object{

        fun <T> success(data: T, message: String): Resource<T> {
            return Resource(Status.SUCCESS, data, message)
        }

        fun <T> success(message: String): Resource<T> {
            return Resource(Status.SUCCESS, null, message)
        }

        fun <T> error(message: String): Resource<T> {
            return Resource(Status.ERROR, null, message)
        }

        fun <T> error(data: T, message: String): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }
    }

}