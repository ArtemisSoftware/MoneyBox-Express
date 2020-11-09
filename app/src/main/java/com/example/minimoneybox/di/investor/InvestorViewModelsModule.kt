package com.example.minimoneybox.di.investor

import androidx.lifecycle.ViewModel
import com.example.minimoneybox.di.ViewModelKey
import com.example.minimoneybox.ui.investor.InvestorViewModel
import com.example.minimoneybox.ui.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
public abstract class InvestorViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(InvestorViewModel::class)
    abstract fun bindInvestorViewModel(viewModel: InvestorViewModel): ViewModel
}