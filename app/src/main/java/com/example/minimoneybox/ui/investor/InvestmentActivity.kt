package com.example.minimoneybox.ui.investor

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.minimoneybox.R
import com.example.minimoneybox.api.models.Product
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

        intent.extras?.let{
            activityBinding.product = it.getParcelable<Product>(getString(R.string.argument_product))
        }

    }


    private fun setupViews() {

        activityBinding.btnPayment.setOnClickListener {

            viewModel.addPayment(activityBinding.product.id, 10.0)
        }
    }

}