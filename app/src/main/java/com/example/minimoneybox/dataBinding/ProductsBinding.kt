package com.example.minimoneybox.dataBinding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minimoneybox.api.models.Product
import com.example.minimoneybox.ui.investor.adapters.OnProductListener
import com.example.minimoneybox.ui.investor.adapters.ProductRecyclerAdapter


class ProductsBinding {

    companion object{

        @JvmStatic
        @BindingAdapter("products", "listener")
        fun setProducts(view: RecyclerView, items: List<Product>?, listener : OnProductListener) {

            items?.let {

                val layoutManager = view.layoutManager

                if (layoutManager == null) {
                    view.layoutManager = LinearLayoutManager(view.context)
                }


                var adapter: ProductRecyclerAdapter


                if (view.adapter  == null) {
                    adapter = ProductRecyclerAdapter(view.context, items.toMutableList(), listener)
                    view.adapter = adapter
                } else {
                    adapter = view.adapter as ProductRecyclerAdapter
                    adapter.update(items)
                }
            }
        }



    }





}