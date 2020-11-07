package com.example.minimoneybox.di

import com.example.minimoneybox.di.Login.LoginScope
import com.example.minimoneybox.di.Login.LoginViewModelsModule
import com.example.minimoneybox.ui.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector




@Module
abstract class ActivityBuildersModule {


    @LoginScope
    @ContributesAndroidInjector(modules = [LoginViewModelsModule::class])
    abstract fun contributeLoginActivity(): LoginActivity


}