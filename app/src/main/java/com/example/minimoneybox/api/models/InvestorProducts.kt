package com.example.minimoneybox.api.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class InvestorProducts  (

    @SerializedName("TotalPlanValue")
    val totalPlanValue: Double,

    @SerializedName("ProductResponses")
    val products: List<Product>


)

@Parcelize
data class Product (

    @SerializedName("Id")
    val id: Int,

    @SerializedName("PlanValue")
    val planValue: Double,

    @SerializedName("Moneybox")
    val moneybox: Double,

    @SerializedName("Product")
    val productInfo: ProductInfo
) : Parcelable

@Parcelize
data class ProductInfo (

    @SerializedName("Name")
    val name: String
) : Parcelable