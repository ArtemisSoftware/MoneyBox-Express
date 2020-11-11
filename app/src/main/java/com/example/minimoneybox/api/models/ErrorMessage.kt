package com.example.minimoneybox.api.models

import com.google.gson.annotations.SerializedName

class ErrorMessage  (

    @SerializedName("Message")
    val message: String,

    @SerializedName("Name")
    val name: String


)