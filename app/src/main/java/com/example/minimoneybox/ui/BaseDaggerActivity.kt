package com.example.minimoneybox.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.minimoneybox.R
import com.example.minimoneybox.databinding.ActivityBaseDaggerBinding
import com.example.minimoneybox.di.ViewModelProviderFactory
import com.example.minimoneybox.utils.viewmodels.BaseViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseDaggerActivity : DaggerAppCompatActivity(){

    private lateinit var activityBaseBinding: ActivityBaseDaggerBinding
    protected lateinit var activityBinding: ViewDataBinding


    @Inject
    lateinit var providerFactory: ViewModelProviderFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base_dagger)
        activityBinding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, getLayout(), activityBaseBinding.activityContent, false)
        activityBaseBinding.activityContent.addView(activityBinding.getRoot())

        initActivity(savedInstanceState)
        activityBaseBinding.setLifecycleOwner(this)
        activityBaseBinding.setBaseviewmodel(getViewModel())

    }


    /**
     * Method that initiates activity variables
     */
    protected abstract fun initActivity(savedInstanceState: Bundle?)


    /**
     * Method that returns the layout of the activity
     */
    protected abstract fun getLayout(): Int


    /**
     * Method that returns the viewmodel
     */
    protected abstract fun getViewModel(): BaseViewModel


}