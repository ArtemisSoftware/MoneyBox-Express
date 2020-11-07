package com.example.minimoneybox.api

import com.example.minimoneybox.api.models.Session
import com.example.minimoneybox.api.requests.Login
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface MoneyBoxApi {

    @POST("users/login")
    fun login(@Body userLogin: Login): Single<Session>
}