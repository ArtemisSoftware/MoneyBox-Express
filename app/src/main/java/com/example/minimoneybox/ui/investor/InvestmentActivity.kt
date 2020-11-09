package com.example.minimoneybox.ui.investor

import com.example.minimoneybox.R
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import com.example.minimoneybox.databinding.ActivityInvestmentBinding
import com.example.minimoneybox.di.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class InvestmentActivity  : DaggerAppCompatActivity() {

    lateinit var activityBinding : ActivityInvestmentBinding

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory


    lateinit private var viewModel: InvestorViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, providerFactory)[InvestorViewModel::class.java]

        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_investment)

        ButterKnife.bind(this)
    }
}