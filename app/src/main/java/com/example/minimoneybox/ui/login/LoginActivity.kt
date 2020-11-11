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
import com.example.minimoneybox.di.ViewModelProviderFactory
import com.example.minimoneybox.ui.investor.ProductsActivity
import com.example.minimoneybox.utils.PreferencesUtil
import com.example.minimoneybox.utils.Resouce
import com.google.android.material.textfield.TextInputLayout
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.NotEmpty
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : DaggerAppCompatActivity () , Validator.ValidationListener {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit private var viewModel: LoginViewModel

    lateinit var btn_sign_in : Button
    lateinit var til_email : TextInputLayout

    @NotEmpty(message = "Field cannot be empty")
    lateinit var et_email : EditText

    lateinit var til_password : TextInputLayout

    @NotEmpty(message = "Field cannot be empty 33")
    lateinit var et_password : EditText
    lateinit var til_name : TextInputLayout
    lateinit var et_name : EditText
    lateinit var animation : LottieAnimationView

    lateinit var validator: Validator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        validator = Validator(this);
        validator.setValidationListener(this);


        setupViews()

        viewModel = ViewModelProviders.of(this, providerFactory)[LoginViewModel::class.java]

        subscribeObservers()
    }

    private fun subscribeObservers(){

        viewModel.observeMessage().observe(this, object : Observer<Resouce<String>> {

            override fun onChanged(resource: Resouce<String>){

                when (resource.status) {

                    Resouce.Status.SUCCESS -> {
                        initInvestor((resource.data as String))
                    }

                    Resouce.Status.ERROR -> {}

                    else -> { // Note the block
                        print("not found")
                    }
                }
            }
        })

    }


    private fun initInvestor(token : String){
        PreferencesUtil.saveInvestor(applicationContext, et_name.text.toString(), token)

        val intent = Intent(this, ProductsActivity::class.java).apply {
            putExtra(getString(R.string.argument_investor_name), et_name.text.toString())
        }
        startActivity(intent)

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

        //TODO: remove, just for test
        et_email.setText("jaeren+androidtest@moneyboxapp.com")
        et_password.setText("P455word12")

        btn_sign_in.setOnClickListener {
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
    }

    override fun onValidationSucceeded() {

        animation.playAnimation()
        viewModel.login(et_email.text.toString(), et_password.text.toString())
    }
}
