package com.example.minimoneybox.ui.investor.adapters

import android.view.View
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.minimoneybox.databinding.ItemProductBinding


class ProductViewHolder (@NonNull itemView: View, val listener : OnProductListener) :  RecyclerView.ViewHolder(itemView), View.OnClickListener{

    var binding: ItemProductBinding = DataBindingUtil.bind<ItemProductBinding>(itemView)!!

    init {

        itemView.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        listener.OnProductClick(binding.product)
    }


}