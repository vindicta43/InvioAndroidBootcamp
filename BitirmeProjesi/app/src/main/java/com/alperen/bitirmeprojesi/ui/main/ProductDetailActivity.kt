package com.alperen.bitirmeprojesi.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.navArgs
import com.alperen.bitirmeprojesi.databinding.ActivityProductDetailBinding
import com.alperen.bitirmeprojesi.ui.viewmodel.MainViewModel
import com.alperen.bitirmeprojesi.utils.AppUtils
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import java.util.Collections

@AndroidEntryPoint
class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private val args: ProductDetailActivityArgs by navArgs()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        binding.instance = this@ProductDetailActivity
        setQuantity()
        binding.foodData = args.foodData
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        Log.d("cartList", "init: " + MainActivity.cartFoodList.toString())
        with(binding) {
            Glide.with(this@ProductDetailActivity)
                .load(AppUtils.IMAGE_URL + args.foodData.yemek_resim_adi).into(ivFoodImage)
        }
    }

    // TODO: arg kullan cumadan sonra
    fun increase() {
        with(binding) {
            val filteredCartFood =
                MainActivity.cartFoodList.singleOrNull { f -> args.foodData.sepet_yemek_id == f.sepet_yemek_id }

            if (MainActivity.cartFoodList.contains(filteredCartFood)) {
                val itemPos = MainActivity.cartFoodList.indexOf(filteredCartFood)
                filteredCartFood!!.yemek_siparis_adet++
                MainActivity.cartFoodList[itemPos] = filteredCartFood
                tvQuantity.text = filteredCartFood.yemek_siparis_adet.toString()
            } else {
                args.foodData.yemek_siparis_adet++
                MainActivity.cartFoodList.add(args.foodData)
                tvQuantity.text = args.foodData.yemek_siparis_adet.toString()
            }

            Log.d("cartList", "updated: " + MainActivity.cartFoodList.toString())
        }
    }

    // TODO: arg kullan cumadan sonra
    fun decrease() {
        with(binding) {
            val filteredCartFood =
                MainActivity.cartFoodList.singleOrNull { f -> args.foodData.sepet_yemek_id == f.sepet_yemek_id }

            if (MainActivity.cartFoodList.contains(filteredCartFood)) {
                val itemPos = MainActivity.cartFoodList.indexOf(filteredCartFood)
                if (filteredCartFood!!.yemek_siparis_adet > 1) {
                    filteredCartFood.yemek_siparis_adet--
                    MainActivity.cartFoodList[itemPos] = filteredCartFood
                    tvQuantity.text = filteredCartFood.yemek_siparis_adet.toString()
                } else {
                    MainActivity.cartFoodList.removeAt(itemPos)
                    args.foodData.yemek_siparis_adet = 0
                    tvQuantity.text = "0"
                }
            } else {
                Toast.makeText(this@ProductDetailActivity, "Ürün yok", Toast.LENGTH_SHORT).show()
            }

            Log.d("cartList", "updated: " + MainActivity.cartFoodList.toString())
        }
    }

    fun setQuantity() {
        val quantity =
            MainActivity.cartFoodList.singleOrNull { f -> args.foodData.sepet_yemek_id == f.sepet_yemek_id }?.yemek_siparis_adet ?: 0

        with(binding) {
            tvQuantity.text = quantity.toString()
        }
    }
}