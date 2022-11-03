package com.alperen.bitirmeprojesi.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alperen.bitirmeprojesi.databinding.LayoutFoodItemBinding
import com.alperen.bitirmeprojesi.model.Food
import com.alperen.bitirmeprojesi.utils.Constants
import com.alperen.bitirmeprojesi.utils.ItemClickedCallback
import com.bumptech.glide.Glide

class HomeRecyclerViewAdapter(val list: List<Food>, val callback: ItemClickedCallback) : RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeViewHolder>() {

    inner class HomeViewHolder(binding: LayoutFoodItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: LayoutFoodItemBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = LayoutFoodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        with(holder.binding) {
            foodData = list[position]
            Glide.with(root).load(Constants.IMAGE_URL+list[position].yemek_resim_adi).into(ivFoodImage)
            root.setOnClickListener { callback.onItemClick(list[position]) }
        }
    }

    override fun getItemCount(): Int = list.size
}