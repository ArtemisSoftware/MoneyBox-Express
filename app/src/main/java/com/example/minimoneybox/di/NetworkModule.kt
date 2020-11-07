package com.example.minimoneybox.di

import com.example.minimoneybox.api.ApiConstants
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            //.client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}