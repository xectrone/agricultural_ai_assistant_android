package com.xectrone.agricultural_ai_assistant.ui.home_screen

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PhotoApiService {
    @Multipart
    @POST("upload/photo")
    suspend fun uploadPhoto(@Part photo: MultipartBody.Part): Response<FeedbackResponse>
}

// Data class to parse server response
data class FeedbackResponse(
    val feedback: String
)
