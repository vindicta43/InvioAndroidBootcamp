package com.alperen.bitirmeprojesi.data

import com.alperen.bitirmeprojesi.model.Food
import com.alperen.bitirmeprojesi.model.FoodResponse
import javax.inject.Inject

class FoodRepository (private val foodDataSource: FoodDataSource) {

    suspend fun getFoods(): List<Food>? = foodDataSource.getFoods()
}