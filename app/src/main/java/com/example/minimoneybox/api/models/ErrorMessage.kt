package com.example.minimoneybox.api.models

import com.google.gson.annotations.SerializedName

class ErrorMessage  (

    @SerializedName("Message")
    val message: String,

    @SerializedName("Name")
    val name: String,

    @SerializedName("ValidationErrors")
    val errors: List<ValidationError>


){
    override fun toString(): String {
        var message = name + "\n" + message + "\n\n";

        for (item in errors){
            message += item.name + ": " + item.message + "\n"
        }

        return message
    }
}

class ValidationError  (

    @SerializedName("Name")
    val name: String,

    @SerializedName("Message")
    val message: String
)