package com.alperen.bitirmeprojesi.utils

import android.app.AlertDialog
import android.content.Context
import com.google.android.material.textfield.TextInputLayout

object AppUtils {
    const val BASE_URL = "http://kasimadalan.pe.hu/"
    const val IMAGE_URL = "http://kasimadalan.pe.hu/yemekler/resimler/"

    const val RESULT_OK = 1
    const val RESULT_ERROR = 0

    const val SHARED_PREF_NAME = "sharedPrefName"
    const val SHARED_PREF_KEY = "sharedPrefKey"

    const val FIELD_MSG = "This field must be filled"
    const val FILL_EMPTY_SPACES = "Please fill empty spaces"
    const val ACC_CREATED = "Account created successfully"
    const val ACC_RESET_MAIL = "Password reset email send successfully"
    const val ERR_OCCURRED = "An error occurred"
    const val LOGOUT = "Logged out successfully"

    fun setError(layout: TextInputLayout, msg: String?) {
        if (msg == null) {
            layout.apply {
                isErrorEnabled = false
            }
        } else {
            layout.apply {
                isErrorEnabled = true
                error = msg
            }
        }
    }

    fun createDialog(ctx: Context, msg: String?) {
        AlertDialog.Builder(ctx)
            .setPositiveButton("Okay") { _, _ -> }
            .setMessage(msg)
            .show()
    }
}