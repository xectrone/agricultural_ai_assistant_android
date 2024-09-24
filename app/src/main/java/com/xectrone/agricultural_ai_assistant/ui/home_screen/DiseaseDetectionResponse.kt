package com.xectrone.agricultural_ai_assistant.ui.home_screen

data class DiseaseDetectionResponse(
    val results: List<DiseaseResult>
)

data class DiseaseResult(
    val label: String,
    val confidence: String,
    val description: String = "No description available for this disease.",
    val cause: String= "No description available for this disease.",
    val treatment: String = "No treatment available for this disease.",
    val prevention: String = "No prevention available for this disease."
)

val test = DiseaseResult(confidence = "98.07%", description = "No description available for this disease.", label = "Grape: Black Rot")


