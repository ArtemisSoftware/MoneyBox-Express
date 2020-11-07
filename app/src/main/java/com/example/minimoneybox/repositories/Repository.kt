package com.example.minimoneybox.repositories

import com.example.minimoneybox.api.MoneyBoxApi
import com.example.minimoneybox.api.models.Session
import com.example.minimoneybox.api.requests.User
import io.reactivex.Single

class Repository(private val api : MoneyBoxApi) {

    fun authenticate(email: String, password: String): Single<Session> {
        return api.login(User(email, password, "ANYTHING"))
    }

}