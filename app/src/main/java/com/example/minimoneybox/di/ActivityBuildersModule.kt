package com.example.minimoneybox.di

import com.example.minimoneybox.di.Login.LoginScope
import com.example.minimoneybox.ui.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector




@Module
abstract class ActivityBuildersModule {


    @LoginScope
    //@ContributesAndroidInjector(modules = [AutenticacaoViewModelsModule::class, AutenticacaoModule::class])
    abstract fun contributeLoginActivity(): LoginActivity


}