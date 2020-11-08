package com.example.minimoneybox.dataBinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minimoneybox.api.models.Product
import com.example.minimoneybox.ui.products.adapters.ProductRecyclerAdapter


class ProductsBinding {

    @BindingAdapter("products")
    fun setProducts(view: RecyclerView, items: List<Product>) {

        if (items == null) {
            return
        }
        val layoutManager = view.layoutManager

        if (layoutManager == null) {
            view.layoutManager = LinearLayoutManager(view.context)
        }


        var adapter: ProductRecyclerAdapter = view.adapter as ProductRecyclerAdapter

        if (adapter == null) {
            adapter = ProductRecyclerAdapter(view.context, items.toMutableList())
            view.adapter = adapter
        } else {
            adapter.update(items)
        }
    }

}