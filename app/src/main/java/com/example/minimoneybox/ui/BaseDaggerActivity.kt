package com.example.minimoneybox.ui

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import androidx.core.content.IntentCompat
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


abstract class BaseDaggerActivity : DaggerAppCompatActivity(), DialogInterface.OnKeyListener{

    private lateinit var activityBaseBinding: ActivityBaseDaggerBinding
    protected lateinit var activityBinding: ViewDataBinding


    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private lateinit var listenerActivity : View.OnClickListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base_dagger)
        activityBinding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, getLayout(), activityBaseBinding.activityContent, false)
        activityBaseBinding.activityContent.addView(activityBinding.getRoot())

        initActivity(savedInstanceState)
        activityBaseBinding.setLifecycleOwner(this)
        activityBaseBinding.setBaseviewmodel(getViewModel())

        listenerActivity = object : View.OnClickListener {
            override fun onClick(v: View?) {
                initLogin()
            }
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

    }


    /**
     * Method that initiates the login activity
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


    protected open fun showError(resource: Resource<String>){

        when(resource.data){

            "401" ->{

                MessagesUtil.error(this, resource.message, listenerActivity)

            }
            else -> {
                MessagesUtil.error(this, resource.message)
            }

        }

    }

    override fun onKey(dialog: DialogInterface?, keyCode: Int, event: KeyEvent?): Boolean {

        initLogin()
        return true
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