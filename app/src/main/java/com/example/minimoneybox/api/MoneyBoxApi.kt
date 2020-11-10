package com.example.minimoneybox.api

import com.example.minimoneybox.api.models.InvestorProducts
import com.example.minimoneybox.api.models.PaymentReceipt
import com.example.minimoneybox.api.models.Session
import com.example.minimoneybox.api.models.requests.Payment
import com.example.minimoneybox.api.models.requests.User
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface MoneyBoxApi {

    @Headers("Content-Type: application/json", "AppId: 3a97b932a9d449c981b595", "appVersion: 7.15.0", "apiVersion: 3.0.0")
    @POST("users/login")
    fun login(@Body userLogin: User): Single<Session>

    @Headers("Content-Type: application/json", "AppId: 3a97b932a9d449c981b595", "appVersion: 7.15.0", "apiVersion: 3.0.0")
    @GET("investorproducts")
    fun getInvestorProducts(@Header("Authorization") token: String): Observable<InvestorProducts>

    @Headers("Content-Type: application/json", "AppId: 3a97b932a9d449c981b595", "appVersion: 7.15.0", "apiVersion: 3.0.0")
    @POST("oneoffpayments")
    fun addPayment(@Header("Authorization") token: String, @Body payment: Payment): Observable<PaymentReceipt>
}