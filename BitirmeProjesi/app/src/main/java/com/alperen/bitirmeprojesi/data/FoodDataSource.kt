package com.alperen.bitirmeprojesi.data

import com.alperen.bitirmeprojesi.model.Food
import com.alperen.bitirmeprojesi.model.FoodResponse
import com.alperen.bitirmeprojesi.network.FoodDao
import com.alperen.bitirmeprojesi.network.RetrofitClient
import com.alperen.bitirmeprojesi.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FoodDataSource @Inject constructor(private val service: FoodDao?) {

    suspend fun getFoods(): List<Food>? = withContext(Dispatchers.IO) {
        service?.getFoods()?.yemekler
    }
}