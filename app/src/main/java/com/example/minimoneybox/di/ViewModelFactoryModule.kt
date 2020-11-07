package com.example.minimoneybox.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module


@Module
public abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(modelProviderFactory: ViewModelProviderFactory?): ViewModelProvider.Factory?

}