package com.example.minimoneybox.ui.investor

import android.os.Bundle
import com.example.minimoneybox.R
import com.example.minimoneybox.di.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ProductsActivity : DaggerAppCompatActivity() {


    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
    }
}