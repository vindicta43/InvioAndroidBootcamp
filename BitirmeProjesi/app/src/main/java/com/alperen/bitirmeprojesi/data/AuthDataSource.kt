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
                    databaseInstance.getReference("users")
                        .child(authInstance.uid.toString())
                        .setValue(User(authInstance.uid.toString(), fullName, email))
                        .addOnSuccessListener {
                            callback.onFinished(
                                AppUtils.RESULT_OK,
                                AppUtils.ACC_CREATED
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
                                AppUtils.ERR_OCCURRED
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
                        AppUtils.ERR_OCCURRED
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
                        AppUtils.ERR_OCCURRED
                    )
                }
        }

    suspend fun forgotPassword(email: String, callback: IAuthCallback) =
        withContext(Dispatchers.Main) {
            callback.onProgress()
            authInstance.sendPasswordResetEmail(email)
                .addOnSuccessListener {
                    callback.onFinished(
                        AppUtils.RESULT_OK,
                        AppUtils.ACC_RESET_MAIL
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
                        AppUtils.ERR_OCCURRED
                    )
                }
        }
}