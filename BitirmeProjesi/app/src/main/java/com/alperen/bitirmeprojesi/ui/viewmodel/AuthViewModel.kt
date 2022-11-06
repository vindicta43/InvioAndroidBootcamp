package com.alperen.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.alperen.bitirmeprojesi.data.AuthRepository
import com.alperen.bitirmeprojesi.utils.IAuthCallback
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {
    @Inject
    lateinit var authInstance: FirebaseAuth

    fun signUp(fullName: String, email: String, password: String, callback: IAuthCallback) {
        CoroutineScope(Dispatchers.Main).launch {
            authRepository.signUp(fullName, email, password, callback)
        }
    }

    fun signIn(email: String, password: String, callback: IAuthCallback) {
        CoroutineScope(Dispatchers.Main).launch {
            authRepository.signIn(email, password, callback)
        }
    }

    fun forgotPassword(email: String, callback: IAuthCallback) {
        CoroutineScope(Dispatchers.Main).launch {
            authRepository.forgotPassword(email, callback)
        }
    }

    fun logout(callback: IAuthCallback) {
        CoroutineScope(Dispatchers.Main).launch {
            authRepository.logout(callback)
        }
    }
}