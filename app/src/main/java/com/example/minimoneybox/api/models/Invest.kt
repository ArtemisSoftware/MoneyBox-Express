package com.example.minimoneybox.api.models

import com.google.gson.annotations.SerializedName

data class Invest(

    @SerializedName("Moneybox")
    val moneybox: Double
)