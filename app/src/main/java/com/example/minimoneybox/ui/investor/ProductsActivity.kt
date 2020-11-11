package com.example.minimoneybox.ui.investor

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.ViewModelProviders
import com.example.minimoneybox.R
import com.example.minimoneybox.api.models.Product
import com.example.minimoneybox.databinding.ActivityProductsBinding
import com.example.minimoneybox.ui.BaseDaggerActivity
import com.example.minimoneybox.ui.investor.adapters.OnProductListener
import com.example.minimoneybox.utils.viewmodels.BaseViewModel

class ProductsActivity : BaseDaggerActivity(), OnProductListener {

    lateinit var activityProductsBinding: ActivityProductsBinding

    lateinit private var viewModel: InvestorViewModel



    override fun initActivity(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, providerFactory)[InvestorViewModel::class.java]

        activityProductsBinding = activityBinding as ActivityProductsBinding

        activityProductsBinding.setLifecycleOwner(this)
        activityProductsBinding.setViewmodel(viewModel)
        activityProductsBinding.setListener(this)


        if(savedInstanceState == null) {
            getIncomingIntent();
        }
        else{
            activityProductsBinding.investorName = savedInstanceState.getString(getString(R.string.argument_investor_name))
        }

    }

    override fun getLayout(): Int {
        return R.layout.activity_products
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }


    private fun getIncomingIntent() {
        intent.extras?.let{
            activityProductsBinding.investorName = it.getString(getString(R.string.argument_investor_name))
        }
    }



    override fun OnProductClick(product: Product?) {
        val intent = Intent(this, InvestmentActivity::class.java).apply {
            putExtra(getString(R.string.argument_product), product)
        }
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getProducts()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

        intent.extras?.let{
            outState.putString(getString(R.string.argument_investor_name), it.getString(getString(R.string.argument_investor_name)))
        }
    }
}