package com.example.minimoneybox.ui.investor

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.minimoneybox.R
import com.example.minimoneybox.databinding.ActivityProductsBinding
import com.example.minimoneybox.di.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ProductsActivity : DaggerAppCompatActivity() {

    lateinit var activityBinding : ActivityProductsBinding;

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory


    lateinit private var viewModel: InvestorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, providerFactory)[InvestorViewModel::class.java]

        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_products)
        activityBinding.setLifecycleOwner(this)
        activityBinding.setViewmodel(viewModel)

        viewModel.getProducts()
    }
}