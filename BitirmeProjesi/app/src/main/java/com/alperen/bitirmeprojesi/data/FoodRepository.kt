package com.alperen.bitirmeprojesi.data

import com.alperen.bitirmeprojesi.model.CartFood
import com.alperen.bitirmeprojesi.model.Food
import java.util.ArrayList

class FoodRepository(private val foodDataSource: FoodDataSource) {

    suspend fun getFoods(): List<Food>? = foodDataSource.getFoods()

    suspend fun checkout(cartFoodList: ArrayList<CartFood>) = foodDataSource.checkout(cartFoodList)

    suspend fun getOrders(uid: String) = foodDataSource.getOrders(uid)

    suspend fun deleteOrder(foodId: Int, uid: String) = foodDataSource.deleteOrder(foodId, uid)
}