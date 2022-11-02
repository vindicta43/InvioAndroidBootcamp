package com.alperen.bitirmeprojesi.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private var INSTANCE: Retrofit? = null

        fun getInstance(baseUrl: String): Retrofit? {
            if (INSTANCE == null) {
                return Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return INSTANCE
        }
    }
}