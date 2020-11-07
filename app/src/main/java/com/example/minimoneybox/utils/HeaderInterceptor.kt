package com.example.minimoneybox.utils

import com.example.minimoneybox.api.ApiConstants
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor{

    companion object {
        const val APP_ID = "AppId"
        const val CONTENT_TYPE = "Content-Type"
        const val APP_VERSION = "appVersion"
        const val API_VERSION = "apiVersion"
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest = chain.request().newBuilder()
            .addHeader(APP_ID, ApiConstants.APP_ID)
            .addHeader(CONTENT_TYPE, ApiConstants.CONTENT_TYPE)
            .addHeader(APP_VERSION, ApiConstants.APP_VERSION)
            .addHeader(API_VERSION, ApiConstants.API_VERSION)
            .build()

        return  chain.proceed(newRequest)
    }
}