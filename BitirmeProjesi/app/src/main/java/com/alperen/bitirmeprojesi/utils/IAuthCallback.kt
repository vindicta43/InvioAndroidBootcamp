package com.alperen.bitirmeprojesi.utils

interface IAuthCallback {
    fun onProgress()
    fun onFinished(resultCode: Int, msg: String?)
}