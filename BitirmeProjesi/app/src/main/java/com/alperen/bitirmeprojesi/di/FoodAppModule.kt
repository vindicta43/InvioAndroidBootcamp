package com.alperen.bitirmeprojesi.di

import com.alperen.bitirmeprojesi.data.AuthDataSource
import com.alperen.bitirmeprojesi.data.AuthRepository
import com.alperen.bitirmeprojesi.data.FoodDataSource
import com.alperen.bitirmeprojesi.data.FoodRepository
import com.alperen.bitirmeprojesi.network.FoodDao
import com.alperen.bitirmeprojesi.network.RetrofitClient
import com.alperen.bitirmeprojesi.utils.AppUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FoodAppModule {

    @Provides
    @Singleton
    fun provideRetrofitService(): FoodDao? {
        return RetrofitClient.getInstance(AppUtils.BASE_URL)?.create(FoodDao::class.java)
    }

    @Provides
    @Singleton
    fun provideFoodDataSource(impl: FoodDao?): FoodDataSource {
        return FoodDataSource(impl)
    }

    @Provides
    @Singleton
    fun provideFoodRepository(impl: FoodDataSource): FoodRepository {
        return FoodRepository(impl)
    }

    @Provides
    @Singleton
    fun provideFirebaseDatabaseInstance(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuthInstance(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideAuthDataSource(impl: FirebaseAuth, impl2: FirebaseDatabase): AuthDataSource {
        return AuthDataSource(impl, impl2)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(impl: AuthDataSource): AuthRepository {
        return AuthRepository(impl)
    }
}