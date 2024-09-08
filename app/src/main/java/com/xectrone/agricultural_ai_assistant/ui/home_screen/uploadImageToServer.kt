package com.xectrone.agricultural_ai_assistant.ui.home_screen

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


//suspend fun uploadPhotoToServer(uri: Uri, context: Context): FeedbackResponse? {
//    val file = File(uri.path ?: return null)
//    val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
//    val body = MultipartBody.Part.createFormData("photo", file.name, requestFile)
//
//    return try {
//        RetrofitInstance.api.uploadPhoto(body).body()
//    } catch (e: Exception) {
//        e.printStackTrace()
//        null
//    }
//}

suspend fun uploadImageToServer(uri: Uri, context: Context): DiseaseDetectionResponse? {
    val file = File(uri.path ?: return null)
    val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
    val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

    return try {
        val response = RetrofitInstance.api.detectDisease(body)
        if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
