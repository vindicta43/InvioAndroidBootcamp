package com.alperen.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alperen.bitirmeprojesi.data.FoodRepository
import com.alperen.bitirmeprojesi.model.CartFood
import com.alperen.bitirmeprojesi.model.Food
import com.alperen.bitirmeprojesi.model.CRUDResponse
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val foodRepository: FoodRepository) : ViewModel() {
    var foodList = MutableLiveData<List<Food>>()
    var ordersList = MutableLiveData<List<CartFood>>()

    @Inject
    lateinit var authInstance: FirebaseAuth

    fun getFoods() {
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = foodRepository.getFoods()
        }
    }

    fun toCartFood(food: Food): CartFood {
        return CartFood(
            food.yemek_id,
            food.yemek_adi,
            food.yemek_resim_adi,
            food.yemek_fiyat,
            0,
            authInstance.uid!!
        )
    }

    fun checkout(cartFoodList: ArrayList<CartFood>): MutableLiveData<CRUDResponse?> {
        val response = MutableLiveData<CRUDResponse?>()
        CoroutineScope(Dispatchers.Main).launch {
            response.value = foodRepository.checkout(cartFoodList)
        }
        return response
    }

    fun getOrders() {
        CoroutineScope(Dispatchers.Main).launch {
            ordersList.value = foodRepository.getOrders(authInstance.uid!!)
        }
    }

    fun deleteOrder(foodId: Int, uid: String): MutableLiveData<CRUDResponse?> {
        val response = MutableLiveData<CRUDResponse?>()
        CoroutineScope(Dispatchers.Main).launch {
            response.value = foodRepository.deleteOrder(foodId, uid)
        }
        return response
    }
}