package com.alperen.bitirmeprojesi.ui.main.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alperen.bitirmeprojesi.databinding.LayoutCartFoodItemBinding
import com.alperen.bitirmeprojesi.databinding.LayoutFoodItemBinding
import com.alperen.bitirmeprojesi.model.CartFood
import com.alperen.bitirmeprojesi.model.Food
import com.alperen.bitirmeprojesi.ui.main.home.HomeRecyclerViewAdapter
import com.alperen.bitirmeprojesi.utils.AppUtils
import com.alperen.bitirmeprojesi.utils.ItemClickedCallback
import com.bumptech.glide.Glide

class OrdersRecyclerViewAdapter(val list: List<CartFood>, val callback: ItemClickedCallback) :
    RecyclerView.Adapter<OrdersRecyclerViewAdapter.OrdersViewHolder>() {

    inner class OrdersViewHolder(binding: LayoutCartFoodItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: LayoutCartFoodItemBinding

        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val binding =
            LayoutCartFoodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrdersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        with(holder.binding) {
            foodData = list[position]
            Glide.with(root).load(AppUtils.IMAGE_URL + list[position].yemek_resim_adi)
                .into(ivFoodImage)
            root.setOnClickListener { callback.onItemClick(list[position]) }
        }
    }

    override fun getItemCount(): Int = list.size
}