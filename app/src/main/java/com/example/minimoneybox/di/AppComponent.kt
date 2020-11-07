package com.example.minimoneybox.di

import android.app.Application
import com.example.minimoneybox.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class, ActivityBuildersModule::class, ViewModelFactoryModule::class, NetworkModule::class])
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}