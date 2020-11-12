package com.example.minimoneybox.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.minimoneybox.R
import com.example.minimoneybox.databinding.ActivityBaseDaggerBinding
import com.example.minimoneybox.di.ViewModelProviderFactory
import com.example.minimoneybox.ui.login.LoginActivity
import com.example.minimoneybox.utils.MessagesUtil
import com.example.minimoneybox.utils.PreferencesUtil
import com.example.minimoneybox.utils.Resource
import com.example.minimoneybox.utils.viewmodels.BaseViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class BaseDaggerActivity : DaggerAppCompatActivity(){

    private lateinit var activityBaseBinding: ActivityBaseDaggerBinding
    protected lateinit var activityBinding: ViewDataBinding


    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private lateinit var activityListener : View.OnClickListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base_dagger)
        activityBinding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, getLayout(), activityBaseBinding.activityContent, false)
        activityBaseBinding.activityContent.addView(activityBinding.getRoot())

        initActivity(savedInstanceState)
        activityBaseBinding.setLifecycleOwner(this)
        activityBaseBinding.setBaseviewmodel(getViewModel())

        activityListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                initLogin()
            }
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

    }


    /**
     * Method that initiates that closes all activities and starts the login activity
     */
    protected fun initLogin(){

        PreferencesUtil.deleteInvestor(this)

        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(
            Intent.FLAG_ACTIVITY_SINGLE_TOP  or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        )
        startActivity(intent)

        finish()
    }


    /**
     * Method to present errors
     */
    protected open fun showError(resource: Resource<String>){

        when(resource.data){

            "401" ->{

                MessagesUtil.error(this, resource.message, activityListener)

            }
            else -> {
                MessagesUtil.error(this, resource.message)
            }

        }
    }


    //------------------
    //abstract methods
    //------------------


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