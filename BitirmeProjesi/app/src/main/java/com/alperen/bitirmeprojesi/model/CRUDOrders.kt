package com.alperen.bitirmeprojesi.model

data class CRUDOrders(
    var sepet_yemekler: List<CartFood>,
    var success: Int
)