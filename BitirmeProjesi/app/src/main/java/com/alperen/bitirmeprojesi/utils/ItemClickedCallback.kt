package com.alperen.bitirmeprojesi.utils

import com.alperen.bitirmeprojesi.model.CartFood
import com.alperen.bitirmeprojesi.model.Food

interface ItemClickedCallback {
    fun onItemClick(food: Food)
    fun onItemClick(food: CartFood)
}