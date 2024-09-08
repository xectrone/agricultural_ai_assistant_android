package com.xectrone.agricultural_ai_assistant.ui.home_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//@Composable
//fun PhotoFeedbackApp() {
//    var feedback by remember { mutableStateOf<String?>(null) }
//    val context = LocalContext.current
//    CameraCapture(
//        onImageCaptured = { uri ->
//            CoroutineScope(Dispatchers.IO).launch {
//                val response = uploadPhotoToServer(uri, context)
//                feedback = response?.feedback
//            }
//        },
//        onError = {
//            feedback = "Failed to capture photo"
//        }
//    )
//
//    feedback?.let {
//        Text("Feedback from server: $it", modifier = Modifier.padding(16.dp))
//    }
//}
