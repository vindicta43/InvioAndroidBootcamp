package com.alperen.bitirmeprojesi.network

import com.alperen.bitirmeprojesi.model.FoodResponse
import retrofit2.http.GET

interface FoodDao {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getFoods() : FoodResponse
}