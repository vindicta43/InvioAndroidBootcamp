package com.alperen.bitirmeprojesi.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.navArgs
import com.alperen.bitirmeprojesi.databinding.ActivityProductDetailBinding
import com.alperen.bitirmeprojesi.utils.Constants
import com.bumptech.glide.Glide

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    val args: ProductDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        with(binding) {
            foodData = args.foodData
            Glide.with(this@ProductDetailActivity)
                .load(Constants.IMAGE_URL + args.foodData.yemek_resim_adi).into(ivFoodImage)
        }
    }
}