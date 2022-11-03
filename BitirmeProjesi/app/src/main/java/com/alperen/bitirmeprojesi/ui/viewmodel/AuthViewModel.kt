package com.alperen.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.alperen.bitirmeprojesi.data.AuthRepository
import com.alperen.bitirmeprojesi.utils.IAuthCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

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
}