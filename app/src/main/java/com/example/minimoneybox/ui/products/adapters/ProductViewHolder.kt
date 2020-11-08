package com.example.minimoneybox.ui.products.adapters

import android.view.View
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.minimoneybox.databinding.ItemProductBinding


class ProductViewHolder (@NonNull itemView: View) :  RecyclerView.ViewHolder(itemView), View.OnClickListener{

    public var binding: ItemProductBinding = DataBindingUtil.bind<ItemProductBinding>(itemView)!!

    init {

        itemView.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }


}