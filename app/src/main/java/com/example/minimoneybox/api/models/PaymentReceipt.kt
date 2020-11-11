package com.example.minimoneybox.api.models

import com.google.gson.annotations.SerializedName

data class PaymentReceipt (

    @SerializedName("Moneybox")
    val moneybox: Double,


    @SerializedName("Name")
    val name: String,

    @SerializedName("message")
    val message : String
)