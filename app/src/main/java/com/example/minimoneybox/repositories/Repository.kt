package com.example.minimoneybox.repositories

import com.example.minimoneybox.api.MoneyBoxApi
import com.example.minimoneybox.api.models.Session
import com.example.minimoneybox.api.requests.Login
import io.reactivex.Single

class Repository(private val api : MoneyBoxApi) {


    fun authenticate(email: String, password: String): Single<Session> {
        return api.login(Login(email, password, "ANYTHING"))
    }

}