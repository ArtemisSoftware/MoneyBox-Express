package com.example.minimoneybox.api.models

import com.google.gson.annotations.SerializedName

class InvestorProducts (

    @SerializedName("TotalPlanValue")
    val totalPlanValue: Double,

    @SerializedName("ProductResponses")
    val products: List<Product>
)

data class Product(

    @SerializedName("Id")
    val id: Int,

    @SerializedName("PlanValue")
    val planValue: Double,

    @SerializedName("Moneybox")
    val moneybox: Double,

    @SerializedName("Product")
    val productInfo: ProductInfo
)

data class ProductInfo(

    @SerializedName("Name")
    val name: String
)