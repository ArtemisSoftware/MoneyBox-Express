package com.example.minimoneybox.api

import com.example.minimoneybox.api.models.InvestorProducts
import com.example.minimoneybox.api.models.Session
import com.example.minimoneybox.api.models.requests.User
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MoneyBoxApi {

    @POST("users/login")
    fun login(@Body userLogin: User): Single<Session>

    @GET("investorproducts")
    fun getInvestorProducts(@Header("Authorization") token: String): Observable<InvestorProducts>

}