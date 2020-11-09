package com.example.minimoneybox.repositories

import com.example.minimoneybox.api.MoneyBoxApi
import com.example.minimoneybox.api.models.InvestorProducts
import com.example.minimoneybox.api.models.Session
import com.example.minimoneybox.api.models.requests.User
import io.reactivex.Observable
import io.reactivex.Single

class Repository(private val api : MoneyBoxApi, private val token : String) {

    fun authenticate(email: String, password: String): Single<Session> {
        return api.login(User(email, password, "ANYTHING"))
    }


    fun getInvestorProducts(): Observable<InvestorProducts> {
        return api.getInvestorProducts(token)
    }
}