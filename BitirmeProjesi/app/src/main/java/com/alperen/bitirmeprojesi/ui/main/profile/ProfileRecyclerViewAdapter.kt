package com.alperen.bitirmeprojesi.ui.main.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alperen.bitirmeprojesi.databinding.LayoutPreviousOrdersItemBinding
import com.alperen.bitirmeprojesi.model.CartFood
import com.alperen.bitirmeprojesi.utils.AppUtils
import com.alperen.bitirmeprojesi.utils.ItemClickedCallback
import com.bumptech.glide.Glide

class ProfileRecyclerViewAdapter(val list: List<CartFood>, val callback: ItemClickedCallback) :
    RecyclerView.Adapter<ProfileRecyclerViewAdapter.ProfileViewHolder>() {

    inner class ProfileViewHolder(binding: LayoutPreviousOrdersItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: LayoutPreviousOrdersItemBinding

        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = LayoutPreviousOrdersItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        with(holder.binding) {
            foodData = list[position]
            Glide.with(root).load(AppUtils.IMAGE_URL + list[position].yemek_resim_adi)
                .into(ivFoodImage)
            ibDelete.setOnClickListener { callback.onItemClick(list[position]) }
        }
    }

    override fun getItemCount(): Int = list.size
}