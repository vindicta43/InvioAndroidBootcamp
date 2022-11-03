package com.alperen.bitirmeprojesi.data

import com.alperen.bitirmeprojesi.utils.IAuthCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthRepository(private val authDataSource: AuthDataSource) {

    suspend fun signUp(fullName: String, email: String, password: String, callback: IAuthCallback) =
        authDataSource.signUp(fullName, email, password, callback)

    suspend fun signIn(email: String, password: String, callback: IAuthCallback) = authDataSource.signIn(email, password, callback)
}