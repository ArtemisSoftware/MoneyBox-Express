package com.example.minimoneybox.ui.investor

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.minimoneybox.R
import com.example.minimoneybox.api.models.Product
import com.example.minimoneybox.databinding.ActivityInvestmentBinding
import com.example.minimoneybox.di.ViewModelProviderFactory
import com.example.minimoneybox.utils.Resouce
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
        activityBinding.setViewmodel(viewModel)
        activityBinding.setLifecycleOwner(this)


        intent.extras?.let{
            viewModel.product.value = it.getParcelable<Product>(getString(R.string.argument_product))
            setupViews()
        }

    }


    private fun subscribeObservers(){

        viewModel.observeMessage().observe(this, object : Observer<Resouce<String>> {

            override fun onChanged(resource: Resouce<String>){

                when (resource.status) {

                    Resouce.Status.SUCCESS -> {

                    }

                    Resouce.Status.ERROR -> {}

                    else -> { // Note the block
                        print("not found")
                    }
                }
            }
        })

    }





    private fun setupViews() {

        activityBinding.btnPayment.setOnClickListener {

            activityBinding.viewmodel?.product?.value?.let {
                viewModel.addPayment(it.id, 10.0)
            }

        }
    }

}