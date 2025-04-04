package com.xectrone.agricultural_ai_assistant

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.xectrone.agricultural_ai_assistant.ui.home_screen.DiseaseDetectionApp
import com.xectrone.agricultural_ai_assistant.ui.theme.AgriculturalAIAssistantTheme
import java.io.File
import androidx.compose.runtime.*
import com.xectrone.agricultural_ai_assistant.ui.home_screen.DiseaseResult
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.FileInputStream
import java.io.IOException
import java.util.concurrent.TimeUnit


class MainActivity : ComponentActivity() {
    private var selectedImageUri: Uri? = null
    private var detectionResults by mutableStateOf<List<DiseaseResult>?>(null)
    private var search by mutableStateOf(false)


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
            AgriculturalAIAssistantTheme(){
                DiseaseDetectionApp(
                    selectedImageUri = selectedImageUri,
                    detectionResults = detectionResults,
                    onCaptureImageClick = { captureImage() },
                    onSelectImageClick = { selectImageFromGallery() },
                    search = search,
                    onBack = { onBack() }
                )
            }
        }
    }

    private fun captureImage() {
        search = true
        val photoFile = createImageFile()
        selectedImageUri = FileProvider.getUriForFile(
            this,
            "${applicationContext.packageName}.fileprovider",
            photoFile
        )
        cameraLauncher.launch(selectedImageUri)
    }

    private fun selectImageFromGallery() {
        search = true
        galleryLauncher.launch("image/*")
    }

    private fun updateImageInUi(uri: Uri) {
        selectedImageUri = uri
        setContent {
            AgriculturalAIAssistantTheme() {
                DiseaseDetectionApp(
                    selectedImageUri = uri,
                    detectionResults = detectionResults,
                    onCaptureImageClick = { captureImage() },
                    onSelectImageClick = { selectImageFromGallery() },
                    search = search,
                    onBack = { onBack() }

                )
            }
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

        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        val request = Request.Builder()
            .url("https://agricultural-ai-assistant.onrender.com/detect")
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
            val label = item.getString("label")
            val confidence = item.getString("confidence")
            val description = item.getString("description")
            val cause = item.getString("cause")
            val treatment = item.getString("treatment")
            val prevention = item.getString("prevention")

            results.add(DiseaseResult(
                label = label,
                confidence = confidence,
                description = description,
                cause = cause,
                treatment = treatment,
                prevention = prevention))
        }

        return results
    }

    private fun onBack(){search = false}
}

