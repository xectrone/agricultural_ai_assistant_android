package com.xectrone.agricultural_ai_assistant.ui.home_screen

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PhotoDetectionApi {
    @Multipart
    @POST("detect")
    suspend fun detectDisease(
        @Part image: MultipartBody.Part
    ): Response<DiseaseDetectionResponse>
}
