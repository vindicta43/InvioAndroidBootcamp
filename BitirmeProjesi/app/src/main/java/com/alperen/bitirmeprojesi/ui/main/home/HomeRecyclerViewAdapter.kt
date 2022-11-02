package com.alperen.bitirmeprojesi.ui.main.home

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alperen.bitirmeprojesi.databinding.LayoutFoodItemBinding
import com.alperen.bitirmeprojesi.model.Food
import com.alperen.bitirmeprojesi.utils.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class HomeRecyclerViewAdapter(val list: List<Food>) : RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeViewHolder>() {

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
            food = list[position]
            Glide.with(root).load(Constants.IMAGE_URL+list[position].yemek_resim_adi).into(ivFood)
            root.setOnClickListener { Log.d("clickEvent", "clicked to ${food.toString()}") }
        }
    }

    override fun getItemCount(): Int = list.size
}