package com.xectrone.agricultural_ai_assistant.ui.home_screen

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://127.0.0.1:5000/"  // Replace with your server's base URL

    val api: PhotoDetectionApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PhotoDetectionApi::class.java)
    }
}
