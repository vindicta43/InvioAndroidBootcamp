package com.alperen.bitirmeprojesi.data

import com.alperen.bitirmeprojesi.model.User
import com.alperen.bitirmeprojesi.utils.AppUtils
import com.alperen.bitirmeprojesi.utils.IAuthCallback
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class AuthDataSource(
    private val authInstance: FirebaseAuth,
    private val databaseInstance: FirebaseDatabase
) {

    suspend fun signUp(fullName: String, email: String, password: String, callback: IAuthCallback) =
        withContext(Dispatchers.Main) {
            callback.onProgress()
            authInstance.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    val randomId = UUID.randomUUID().toString()

                    databaseInstance.getReference("users")
                        .child(randomId)
                        .setValue(User(randomId, fullName, email))
                        .addOnSuccessListener {
                            callback.onFinished(
                                AppUtils.RESULT_OK,
                                "Account created successfully"
                            )
                        }
                        .addOnFailureListener {
                            callback.onFinished(
                                AppUtils.RESULT_ERROR,
                                it.localizedMessage
                            )
                        }
                        .addOnCanceledListener {
                            callback.onFinished(
                                AppUtils.RESULT_ERROR,
                                "An error occurred"
                            )
                        }
                }
                .addOnFailureListener {
                    callback.onFinished(
                        AppUtils.RESULT_ERROR,
                        it.localizedMessage
                    )
                }
                .addOnCanceledListener {
                    callback.onFinished(
                        AppUtils.RESULT_ERROR,
                        "An error occurred"
                    )
                }
        }

    suspend fun signIn(email: String, password: String, callback: IAuthCallback) =
        withContext(Dispatchers.Main) {
            callback.onProgress()
            authInstance.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    callback.onFinished(
                        AppUtils.RESULT_OK,
                        null
                    )
                }
                .addOnFailureListener {
                    callback.onFinished(
                        AppUtils.RESULT_ERROR,
                        it.localizedMessage
                    )
                }
                .addOnCanceledListener {
                    callback.onFinished(
                        AppUtils.RESULT_ERROR,
                        "An error occurred"
                    )
                }
        }
}