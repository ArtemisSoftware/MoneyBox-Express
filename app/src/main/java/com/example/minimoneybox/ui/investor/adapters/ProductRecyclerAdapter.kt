package com.example.minimoneybox.ui.investor.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.minimoneybox.R
import com.example.minimoneybox.api.models.Product
import com.example.minimoneybox.databinding.ItemProductBinding


class ProductRecyclerAdapter(val context : Context, var products : MutableList<Product>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemProductBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_product, parent, false)
        return ProductViewHolder(binding.getRoot())
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var item = products.get(position)

        (holder as ProductViewHolder).binding.product = item
        (holder as ProductViewHolder).binding.executePendingBindings()
    }

    fun update(items: List<Product>) {
        products.clear()
        products.addAll(items)
        notifyDataSetChanged()
    }
}