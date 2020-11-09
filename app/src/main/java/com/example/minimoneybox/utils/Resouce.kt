package com.example.minimoneybox.utils

class Resouce<T>(val status: Status, val data : T? = null, val message : String) {



    enum class Status {
        SUCCESS, ERROR, LOADING
    }


    companion object{

        fun <T> success(data: T, message: String): Resouce<T> {
            return Resouce(Status.SUCCESS, data, message)
        }

        fun <T> error(message: String): Resouce<T> {
            return Resouce(Status.ERROR, null, message)
        }
    }

}