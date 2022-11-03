package com.alperen.bitirmeprojesi.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.navArgs
import com.alperen.bitirmeprojesi.databinding.ActivityProductDetailBinding
import com.alperen.bitirmeprojesi.utils.AppUtils
import com.bumptech.glide.Glide

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private val args: ProductDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        binding.instance = this@ProductDetailActivity
        binding.foodData = args.foodData
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        with(binding) {
            Glide.with(this@ProductDetailActivity).load(AppUtils.IMAGE_URL+args.foodData.yemek_resim_adi).into(ivFoodImage)
        }
    }

    fun increase() {
        with(binding) {
            var quantity = tvQuantity.text.toString().toInt()
            tvQuantity.text = (++quantity).toString()
        }
    }

    fun decrease() {
        with(binding) {
            val quantity = tvQuantity.text.toString().toInt()
            if (quantity != 0)
                tvQuantity.text = (quantity - 1).toString()
        }
    }
}