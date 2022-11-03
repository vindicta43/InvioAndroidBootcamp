package com.alperen.bitirmeprojesi.data

import com.alperen.bitirmeprojesi.model.Food
import com.alperen.bitirmeprojesi.network.FoodDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDataSource (private val service: FoodDao?) {

    suspend fun getFoods(): List<Food>? = withContext(Dispatchers.IO) {
        service?.getFoods()?.yemekler
    }
}