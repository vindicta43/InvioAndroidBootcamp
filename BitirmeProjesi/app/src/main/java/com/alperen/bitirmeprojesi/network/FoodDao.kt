package com.alperen.bitirmeprojesi.network

import com.alperen.bitirmeprojesi.model.CRUDResponse
import com.alperen.bitirmeprojesi.model.CRUDOrders
import com.alperen.bitirmeprojesi.model.FoodResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodDao {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getFoods(): FoodResponse

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun addFoodToCart(
        @Field("yemek_adi") yemek_adi: String,
        @Field("yemek_resim_adi") yemek_resim_adi: String,
        @Field("yemek_fiyat") yemek_fiyat: Int,
        @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
        @Field("kullanici_adi") kullanici_adi: String
    ): CRUDResponse

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun getOrders(
        @Field("kullanici_adi") kullanici_adi: String
    ): CRUDOrders

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun deleteOrder(
        @Field("sepet_yemek_id") sepet_yemek_id: Int,
        @Field("kullanici_adi") kullanici_adi: String
    ): CRUDResponse
}