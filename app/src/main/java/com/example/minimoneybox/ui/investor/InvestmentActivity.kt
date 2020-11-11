package com.example.minimoneybox.ui.investor

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.minimoneybox.R
import com.example.minimoneybox.api.models.Product
import com.example.minimoneybox.databinding.ActivityInvestmentBinding
import com.example.minimoneybox.ui.BaseDaggerActivity
import com.example.minimoneybox.utils.MessagesUtil
import com.example.minimoneybox.utils.Resouce
import com.example.minimoneybox.utils.viewmodels.BaseViewModel
import com.google.android.material.snackbar.Snackbar
import com.romainpiel.shimmer.Shimmer
import com.yarolegovich.lovelydialog.LovelyInfoDialog
import kotlinx.android.synthetic.main.activity_investment.*


class InvestmentActivity  : BaseDaggerActivity() {

    lateinit var activityInvestmentBinding: ActivityInvestmentBinding



    lateinit private var viewModel: InvestorViewModel


    override fun initActivity(savedInstanceState: Bundle?) {

        viewModel = ViewModelProviders.of(this, providerFactory)[InvestorViewModel::class.java]

        activityInvestmentBinding = activityBinding as ActivityInvestmentBinding

        activityInvestmentBinding.setLifecycleOwner(this)
        activityInvestmentBinding.setViewmodel(viewModel)

        subscribeObservers()

        intent.extras?.let{
            viewModel.product.value = it.getParcelable<Product>(getString(R.string.argument_product))
            setupViews()
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

        viewModel.observeMessage().observe(this, object : Observer<Resouce<String>> {

            override fun onChanged(resource: Resouce<String>){

                when (resource.status) {

                    Resouce.Status.SUCCESS -> {
                        showSuccess(resource.message)
                    }

                    Resouce.Status.ERROR -> {
                        MessagesUtil.error(applicationContext, resource.message)
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
}