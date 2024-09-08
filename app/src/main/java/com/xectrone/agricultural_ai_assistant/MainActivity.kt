package com.xectrone.agricultural_ai_assistant

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.xectrone.agricultural_ai_assistant.ui.home_screen.CAMERA_REQUEST_CODE
import com.xectrone.agricultural_ai_assistant.ui.home_screen.DiseaseDetectionApp
import com.xectrone.agricultural_ai_assistant.ui.home_screen.GALLERY_REQUEST_CODE
import com.xectrone.agricultural_ai_assistant.ui.theme.AgriculturalAIAssistantTheme
import java.io.File
import android.content.Context
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.xectrone.agricultural_ai_assistant.ui.home_screen.DiseaseResult
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.FileInputStream
import java.io.IOException


class MainActivity : ComponentActivity() {
    private var selectedImageUri: Uri? = null
    private var detectionResults by mutableStateOf<List<DiseaseResult>?>(null)

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            selectedImageUri?.let {
                updateImageInUi(it)
                uploadImageAndDetectDisease(it)
            }
        }
    }

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            updateImageInUi(it)
            uploadImageAndDetectDisease(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiseaseDetectionApp(
                selectedImageUri = selectedImageUri,
                detectionResults = detectionResults,
                onCaptureImageClick = { captureImage() },
                onSelectImageClick = { selectImageFromGallery() }
            )
        }
    }

    private fun captureImage() {
        val photoFile = createImageFile()
        selectedImageUri = FileProvider.getUriForFile(
            this,
            "${applicationContext.packageName}.fileprovider",
            photoFile
        )
        cameraLauncher.launch(selectedImageUri)
    }

    private fun selectImageFromGallery() {
        galleryLauncher.launch("image/*")
    }

    private fun updateImageInUi(uri: Uri) {
        selectedImageUri = uri
        setContent {
            DiseaseDetectionApp(
                selectedImageUri = uri,
                detectionResults = detectionResults,
                onCaptureImageClick = { captureImage() },
                onSelectImageClick = { selectImageFromGallery() }
            )
        }
    }

    private fun uploadImageAndDetectDisease(uri: Uri) {
        val context = this
        val contentResolver = context.contentResolver
        val fileDescriptor = contentResolver.openFileDescriptor(uri, "r") ?: return

        Log.d("Upload", "Preparing image for upload")

        val requestBody = fileDescriptor.use {
            val inputStream = FileInputStream(it.fileDescriptor)
            val byteArray = inputStream.readBytes()
            byteArray.toRequestBody("image/jpeg".toMediaTypeOrNull())
        }

        val multipartBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("image", "image.jpg", requestBody)
            .build()

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://05f3-2401-4900-1c2c-380b-7998-5517-7f91-59bc.ngrok-free.app/detect")
            .post(multipartBody)
            .build()

        Log.d("Upload", "Sending request to server")

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Upload Error", "Failed to upload image: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d("Upload", "Received response from server")

                if (response.isSuccessful) {
                    response.body?.string()?.let { responseBody ->
                        val results = parseResults(responseBody)
                        runOnUiThread {
                            Log.d("Upload", "Results: $results")
                            detectionResults = results // Update the state
                        }
                    }
                } else {
                    Log.e("Upload Error", "Server error: ${response.message}")
                }
            }
        })
    }

    private fun createImageFile(): File {
        val storageDir: File = cacheDir
        return File.createTempFile("temp_image", ".jpg", storageDir)
    }

    private fun parseResults(json: String): List<DiseaseResult> {
        val jsonArray = JSONObject(json).getJSONArray("results")
        val results = mutableListOf<DiseaseResult>()

        for (i in 0 until jsonArray.length()) {
            val item = jsonArray.getJSONObject(i)
            val confidence = item.getString("confidence")
            val description = item.getString("description")
            val label = item.getString("label")
            results.add(DiseaseResult(confidence, description, label))
        }

        return results
    }
}

