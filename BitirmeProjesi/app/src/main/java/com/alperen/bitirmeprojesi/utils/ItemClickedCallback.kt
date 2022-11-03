package com.alperen.bitirmeprojesi.utils

import com.alperen.bitirmeprojesi.model.Food

interface ItemClickedCallback {
    fun onItemClick(food: Food)
}