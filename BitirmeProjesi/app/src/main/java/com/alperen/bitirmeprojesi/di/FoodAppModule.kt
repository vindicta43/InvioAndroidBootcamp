package com.alperen.bitirmeprojesi.di

import com.alperen.bitirmeprojesi.data.FoodDataSource
import com.alperen.bitirmeprojesi.data.FoodRepository
import com.alperen.bitirmeprojesi.network.FoodDao
import com.alperen.bitirmeprojesi.network.RetrofitClient
import com.alperen.bitirmeprojesi.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FoodAppModule {

    @Provides
    @Singleton
    fun provideRetrofitService(): FoodDao? {
        return RetrofitClient.getInstance(Constants.BASE_URL)?.create(FoodDao::class.java)
    }

    @Provides
    @Singleton
    fun provideFoodDataSource(service: FoodDao?) : FoodDataSource {
        return FoodDataSource(service)
    }

    @Provides
    @Singleton
    fun provideFoodRepository(foodDataSource: FoodDataSource): FoodRepository {
        return FoodRepository(foodDataSource)
    }
}