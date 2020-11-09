package com.example.minimoneybox.ui.investor.adapters

import com.example.minimoneybox.api.models.Product

interface OnProductListener {

    fun OnProductClick(product : Product)
}