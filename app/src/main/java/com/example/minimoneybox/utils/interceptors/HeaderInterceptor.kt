package com.example.minimoneybox.utils.interceptors

import com.example.minimoneybox.api.ApiConstants
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest = chain.request().newBuilder()
            .addHeader(ApiConstants.APP_ID_, ApiConstants.APP_ID)
            .addHeader(ApiConstants.CONTENT_TYPE_, ApiConstants.CONTENT_TYPE)
            .addHeader(ApiConstants.APP_VERSION_, ApiConstants.APP_VERSION)
            .addHeader(ApiConstants.API_VERSION_, ApiConstants.API_VERSION)
            .build()

        return  chain.proceed(newRequest)
    }
}