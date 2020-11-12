package com.example.minimoneybox.di

import android.app.Application
import com.example.minimoneybox.api.ApiConstants
import com.example.minimoneybox.api.MoneyBoxApi
import com.example.minimoneybox.repositories.Repository
import com.example.minimoneybox.utils.interceptors.HeaderInterceptor
import com.example.minimoneybox.utils.interceptors.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideOkHttpClient(application : Application): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)


        val client : OkHttpClient  =  OkHttpClient.Builder()

            .addInterceptor(loggingInterceptor)
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(NetworkConnectionInterceptor(application))

            .connectTimeout(ApiConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS) //time between each byte read from the server
            .readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.SECONDS) //time between each byte sent to server
            .writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .build()
        return client


    }


    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(okHttpClient : OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Reusable
    @JvmStatic
    fun provideMoneyBoxApiInterface(retrofit: Retrofit): MoneyBoxApi {
        return retrofit.create<MoneyBoxApi>(MoneyBoxApi::class.java)
    }


    @Provides
    @Reusable
    @JvmStatic
    fun provideRepository(api: MoneyBoxApi, token: String): Repository {
        return Repository(api, token)
    }

}