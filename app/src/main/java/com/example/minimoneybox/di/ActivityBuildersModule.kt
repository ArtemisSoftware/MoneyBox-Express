package com.example.minimoneybox.di

import com.example.minimoneybox.di.Login.LoginScope
import com.example.minimoneybox.di.Login.LoginViewModelsModule
import com.example.minimoneybox.di.investor.InvestorScope
import com.example.minimoneybox.di.investor.InvestorViewModelsModule
import com.example.minimoneybox.ui.investor.InvestmentActivity
import com.example.minimoneybox.ui.investor.ProductsActivity
import com.example.minimoneybox.ui.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector




@Module
abstract class ActivityBuildersModule {


    @LoginScope
    @ContributesAndroidInjector(modules = [LoginViewModelsModule::class])
    abstract fun contributeLoginActivity(): LoginActivity

    @InvestorScope
    @ContributesAndroidInjector(modules = [InvestorViewModelsModule::class])
    abstract fun contributeProductsActivity(): ProductsActivity

    @InvestorScope
    @ContributesAndroidInjector(modules = [InvestorViewModelsModule::class])
    abstract fun contributeInvestmentActivity(): InvestmentActivity
}