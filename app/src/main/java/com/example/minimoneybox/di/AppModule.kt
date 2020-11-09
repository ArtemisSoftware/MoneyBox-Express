package com.example.minimoneybox.di

import android.app.Application
import com.example.minimoneybox.utils.PreferencesUtil
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object AppModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideToken(application : Application): String {
        return PreferencesUtil.getInvestorName(application.getApplicationContext())
    }
}