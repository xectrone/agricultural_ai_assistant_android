package com.xectrone.agricultural_ai_assistant.ui.home_screen

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.compose.LocalLifecycleOwner
import java.io.File

@Composable
fun CameraCapture(
    onImageCaptured: (Uri) -> Unit,
    onError: (ImageCaptureException) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    var imageCapture: ImageCapture? = remember { null }

    AndroidView(factory = { context ->
        val previewView = PreviewView(context)
        val cameraProvider = cameraProviderFuture.get()

        imageCapture = ImageCapture.Builder().build()

        val preview = Preview.Builder().build().also {
            it.setSurfaceProvider(previewView.surfaceProvider)
        }

        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
        cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview, imageCapture)
        previewView
    }, modifier = Modifier
        .fillMaxWidth()
        .height(400.dp)
    )

    Button(onClick = {
        val outputOptions = ImageCapture.OutputFileOptions.Builder(createTempFile()).build()
        imageCapture?.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = outputFileResults.savedUri ?: return
                    onImageCaptured(savedUri)
                }

                override fun onError(exception: ImageCaptureException) {
                    onError(exception)
                }
            }
        )
    }) {
        Text("Capture Photo")
    }
}
const val CAMERA_REQUEST_CODE = 1001
const val GALLERY_REQUEST_CODE = 1002


fun startCamera(context: Context, onImageCaptured: (Uri) -> Unit) {
    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    val photoFile = File.createTempFile("temp_image", ".jpg", context.cacheDir)
    val photoUri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", photoFile)
    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
    (context as Activity).startActivityForResult(intent, CAMERA_REQUEST_CODE)
    onImageCaptured(photoUri)
}

fun startGallery(context: Context, onImageSelected: (Uri) -> Unit) {
    val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    (context as Activity).startActivityForResult(intent,  GALLERY_REQUEST_CODE)
    onImageSelected(intent.data!!)
}
