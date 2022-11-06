package com.alperen.bitirmeprojesi.data

import com.alperen.bitirmeprojesi.model.CartFood
import com.alperen.bitirmeprojesi.model.Food
import com.alperen.bitirmeprojesi.model.CRUDResponse
import com.alperen.bitirmeprojesi.network.FoodDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.ArrayList

class FoodDataSource(private val service: FoodDao?) {

    suspend fun getFoods(): List<Food>? = withContext(Dispatchers.IO) {
        service?.getFoods()?.yemekler
    }

    suspend fun checkout(cartFoodList: ArrayList<CartFood>): CRUDResponse? =
        withContext(Dispatchers.IO) {
            cartFoodList.forEach {
                val response = service?.addFoodToCart(
                    it.yemek_adi,
                    it.yemek_resim_adi,
                    it.yemek_fiyat,
                    it.yemek_siparis_adet,
                    it.kullanici_adi
                )

                if (response?.success != 1)
                    return@withContext response
            }
            return@withContext CRUDResponse(1, "Products purchased successfully")
        }

    suspend fun getOrders(uid: String): List<CartFood>? = withContext(Dispatchers.IO) {
        try {
            service?.getOrders(uid)?.sepet_yemekler
        } catch (e: Exception) {
            return@withContext null
        }
    }

    suspend fun deleteOrder(foodId: Int, uid: String): CRUDResponse? = withContext(Dispatchers.IO) {
       service?.deleteOrder(foodId, uid)
    }
}