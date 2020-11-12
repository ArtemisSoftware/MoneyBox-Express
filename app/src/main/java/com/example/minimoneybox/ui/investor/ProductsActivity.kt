package com.example.minimoneybox.ui.investor

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.minimoneybox.R
import com.example.minimoneybox.api.models.Product
import com.example.minimoneybox.databinding.ActivityProductsBinding
import com.example.minimoneybox.ui.BaseDaggerActivity
import com.example.minimoneybox.ui.investor.adapters.OnProductListener
import com.example.minimoneybox.utils.PreferencesUtil
import com.example.minimoneybox.utils.Resource
import com.example.minimoneybox.utils.viewmodels.BaseViewModel
import kotlinx.android.synthetic.main.activity_products.*

/**
 * Activity that shows all the products availble
 */
class ProductsActivity : BaseDaggerActivity(), OnProductListener {

    lateinit var activityProductsBinding: ActivityProductsBinding

    lateinit private var viewModel: InvestorViewModel

    lateinit private var userName: String


    override fun initActivity(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, providerFactory)[InvestorViewModel::class.java]

        activityProductsBinding = activityBinding as ActivityProductsBinding

        activityProductsBinding.setLifecycleOwner(this)
        activityProductsBinding.setViewmodel(viewModel)
        activityProductsBinding.setListener(this)

        setupViews()
        subscribeObservers()

        if(PreferencesUtil.getSessionValidity(this) == false){
            initLogin()
        }
        else {

            if (savedInstanceState == null) {
                getIncomingData();
            } else {
                txt_user.text = savedInstanceState.getString(getString(R.string.argument_investor_name))
            }
        }

    }

    override fun getLayout(): Int {
        return R.layout.activity_products
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }


    /**
     * Method to subscribe observers
     */
    private fun subscribeObservers(){

        viewModel.observeMessage().observe(this, object : Observer<Resource<String>> {

            override fun onChanged(resource: Resource<String>){

                when (resource.status) {

                    Resource.Status.ERROR -> {
                        showError(resource)
                    }

                    else -> {
                        print("not found")
                    }
                }
            }
        })
    }


    private fun setupViews() {

        crl_img_logout.setOnClickListener {
            initLogin()
        }
    }


    /**
     * Method to get data to complete activity
     */
    private fun getIncomingData() {

        userName = PreferencesUtil.getInvestorName(this)
        txt_user.text = userName
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(getString(R.string.argument_investor_name), userName)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        userName = savedInstanceState.getString(getString(R.string.argument_investor_name), "000")
        txt_user.text = userName
    }
}