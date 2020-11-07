package com.example.minimoneybox.di

import com.example.minimoneybox.api.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import java.util.concurrent.TimeUnit;

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient? {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        //val webServiceInterceptor = WebServiceInterceptor()


        val client : OkHttpClient  =  OkHttpClient.Builder()

            .addInterceptor(loggingInterceptor)

            //.addInterceptor(webServiceInterceptor)

            .connectTimeout(ApiConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS) //time between each byte read from the server
            .readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.SECONDS) //time between each byte sent to server
            .writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false) //.addInterceptor(new UrlInterceptor())
            .build()

        return client
    }


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