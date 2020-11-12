package com.example.minimoneybox.ui.investor

import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import android.view.KeyEvent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.minimoneybox.R
import com.example.minimoneybox.api.models.Product
import com.example.minimoneybox.databinding.ActivityInvestmentBinding
import com.example.minimoneybox.ui.BaseDaggerActivity
import com.example.minimoneybox.utils.MessagesUtil
import com.example.minimoneybox.utils.Resource
import com.example.minimoneybox.utils.viewmodels.BaseViewModel
import com.romainpiel.shimmer.Shimmer
import kotlinx.android.synthetic.main.activity_investment.*
import kotlinx.android.synthetic.main.activity_products.*


class InvestmentActivity  : BaseDaggerActivity() {

    lateinit var activityInvestmentBinding: ActivityInvestmentBinding



    lateinit private var viewModel: InvestorViewModel


    override fun initActivity(savedInstanceState: Bundle?) {

        viewModel = ViewModelProviders.of(this, providerFactory)[InvestorViewModel::class.java]

        activityInvestmentBinding = activityBinding as ActivityInvestmentBinding

        activityInvestmentBinding.setLifecycleOwner(this)
        activityInvestmentBinding.setViewmodel(viewModel)

        subscribeObservers()


        if(savedInstanceState == null) {
            getIncomingIntent();
        }
        else{
            viewModel.product.value = savedInstanceState.getParcelable(getString(R.string.argument_product))
        }

    }



    override fun getLayout(): Int {
        return R.layout.activity_investment
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

                    Resource.Status.SUCCESS -> {
                        showSuccess(resource.message)
                    }

                    Resource.Status.ERROR -> {
                        showError(resource)
                    }

                    else -> {
                        print("not found")
                    }
                }

                btn_payment.isEnabled = true
            }
        })

    }

    private fun setupViews() {

        activityInvestmentBinding.btnPayment.setOnClickListener {

            viewModel.product?.value?.let {
                btn_payment.isEnabled = false
                viewModel.addPayment(it.id, 10.0)
            }
        }
    }




    private fun getIncomingIntent() {
        intent.extras?.let{
            viewModel.product.value = it.getParcelable<Product>(getString(R.string.argument_product))
            setupViews()
        }
    }




    /**
     * Method to show the success state
     */
    private fun showSuccess(message : String?){

        message?.let {

            val shimmer = Shimmer()
            shimmer.setDuration(500)
                .setStartDelay(300)
                .setDirection(Shimmer.ANIMATION_DIRECTION_RTL).start(txt_total)

            MessagesUtil.success(this, message)

        }
    }




    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

        intent.extras?.let{
            outState.putParcelable(getString(R.string.argument_product), it.getParcelable<Product>(getString(R.string.argument_product)))
        }
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        viewModel.product.value =  savedInstanceState.getParcelable(getString(R.string.argument_product))
    }
}