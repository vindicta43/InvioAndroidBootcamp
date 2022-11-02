package com.alperen.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alperen.bitirmeprojesi.data.FoodRepository
import com.alperen.bitirmeprojesi.model.Food
import com.alperen.bitirmeprojesi.model.FoodResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(val foodRepository: FoodRepository): ViewModel() {
    var foodList = MutableLiveData<List<Food>>()

    fun getFoods() {
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = foodRepository.getFoods()
        }
    }
}