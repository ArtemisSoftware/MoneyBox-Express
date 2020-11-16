package com.example.minimoneybox.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airbnb.lottie.LottieAnimationView
import com.example.minimoneybox.R
import com.example.minimoneybox.databinding.ActivityLoginBinding
import com.example.minimoneybox.ui.BaseDaggerActivity
import com.example.minimoneybox.ui.investor.ProductsActivity
import com.example.minimoneybox.utils.MessagesUtil
import com.example.minimoneybox.utils.PreferencesUtil
import com.example.minimoneybox.utils.Resource
import com.example.minimoneybox.utils.viewmodels.BaseViewModel
import com.google.android.material.textfield.TextInputLayout
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.NotEmpty


/**
 * A login screen that offers login via email/password.
 */
class LoginActivity :  BaseDaggerActivity() , Validator.ValidationListener {


    lateinit var btn_sign_in : Button
    lateinit var til_email : TextInputLayout
    lateinit var til_password : TextInputLayout
    lateinit var til_name : TextInputLayout
    lateinit var et_name : EditText
    lateinit var animation : LottieAnimationView



    lateinit var activityLoginBinding: ActivityLoginBinding

    lateinit private var viewModel: LoginViewModel

    @NotEmpty(message = "Field cannot be empty")
    lateinit var et_email : EditText

    @NotEmpty(message = "Field cannot be empty")
    lateinit var et_password : EditText

    lateinit var validator: Validator


    override fun initActivity(savedInstanceState: Bundle?) {

        validator = Validator(this);
        validator.setValidationListener(this);

        setupViews()

        viewModel = ViewModelProviders.of(this, providerFactory)[LoginViewModel::class.java]

        activityLoginBinding = activityBinding as ActivityLoginBinding

        activityLoginBinding.setLifecycleOwner(this)
        activityLoginBinding.setViewmodel(viewModel)

        subscribeObservers()
    }

    override fun getLayout(): Int {
        return R.layout.activity_login
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
                        initProducts((resource.data as String))
                    }

                    Resource.Status.ERROR -> {

                        btn_sign_in.isEnabled = true
                        MessagesUtil.error(this@LoginActivity, resource.message)
                    }

                    else -> { // Note the block
                        print("not found")
                    }
                }
            }
        })
    }


    /**
     * Method to initiate the product activity
     */
    private fun initProducts(token : String){

        PreferencesUtil.saveInvestor(this, et_email.text.toString(), et_name.text.toString(), token)

        val intent = Intent(this, ProductsActivity::class.java)
        startActivity(intent)

        finish()
    }


    private fun setupViews() {
        btn_sign_in = findViewById(R.id.btn_sign_in)
        til_email = findViewById(R.id.til_email)
        et_email = findViewById(R.id.et_email)
        til_password = findViewById(R.id.til_password)
        et_password = findViewById(R.id.et_password)
        til_name = findViewById(R.id.til_name)
        et_name = findViewById(R.id.et_name)
        animation = findViewById(R.id.animation)

        //for tests
//        et_email.setText("jaeren+androidtest@moneyboxapp.com")
//        et_password.setText("P455word12")
//        et_name.setText("Milo kraken")

        btn_sign_in.setOnClickListener {
            btn_sign_in.isEnabled = false
            validator.validate();
        }
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>) {

        for (error in errors) {

            val view: View = error.view
            val message = error.getCollatedErrorMessage(this)

            if (view is EditText) {
                (view as EditText).error = message
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        }

        btn_sign_in.isEnabled = true
    }

    override fun onValidationSucceeded() {

        animation.playAnimation()
        viewModel.login(et_email.text.toString(), et_password.text.toString())
    }
}
