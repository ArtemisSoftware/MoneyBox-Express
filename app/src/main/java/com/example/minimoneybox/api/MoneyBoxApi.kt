package com.example.minimoneybox.api

import com.example.minimoneybox.api.models.Session
import com.example.minimoneybox.api.models.requests.User
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface MoneyBoxApi {

    @POST("users/login")
    fun login(@Body userLogin: User): Single<Session>
}