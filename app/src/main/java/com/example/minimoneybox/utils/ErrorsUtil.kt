package com.example.minimoneybox.utils

import com.example.minimoneybox.api.models.ErrorMessage
import com.google.gson.Gson
import retrofit2.HttpException

class ErrorsUtil {

    companion object{

        /**
         * Method to extract http errors
         */
        fun httpErrors(exception: HttpException) : Resource<String>{

            val gson = Gson()

            return when(exception.code()){

                400 ->{
                    val error = gson.fromJson(exception.response()!!.errorBody()!!.charStream().readText(), ErrorMessage::class.java)
                    return Resource.error<String>(exception.code().toString(), error.toString())
                }
                401 ->{
                    val error = gson.fromJson(exception.response()!!.errorBody()!!.charStream().readText(), ErrorMessage::class.java)
                    return Resource.error<String>(exception.code().toString(), error.toString())
                }
                else ->{
                    return Resource.error<String>(exception.message())
                }
            }

        }
    }
}