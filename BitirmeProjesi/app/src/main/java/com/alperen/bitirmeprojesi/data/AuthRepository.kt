package com.alperen.bitirmeprojesi.data

import com.alperen.bitirmeprojesi.utils.IAuthCallback

class AuthRepository(private val authDataSource: AuthDataSource) {

    suspend fun signUp(fullName: String, email: String, password: String, callback: IAuthCallback) =
        authDataSource.signUp(fullName, email, password, callback)

    suspend fun signIn(email: String, password: String, callback: IAuthCallback) =
        authDataSource.signIn(email, password, callback)

    suspend fun forgotPassword(email: String, callback: IAuthCallback) = authDataSource.forgotPassword(email, callback)

    suspend fun logout(callback: IAuthCallback) = authDataSource.logout(callback)

}