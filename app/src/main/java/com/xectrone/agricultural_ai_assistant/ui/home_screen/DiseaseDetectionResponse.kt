package com.xectrone.agricultural_ai_assistant.ui.home_screen

data class DiseaseDetectionResponse(
    val results: List<DiseaseResult>
)

data class DiseaseResult(
    val confidence: String,
    val description: String,
    val label: String
)
