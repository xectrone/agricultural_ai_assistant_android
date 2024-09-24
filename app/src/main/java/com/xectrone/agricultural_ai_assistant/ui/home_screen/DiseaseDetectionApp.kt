package com.xectrone.agricultural_ai_assistant.ui.home_screen


import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.xectrone.agricultural_ai_assistant.ui.theme.CustomTypography
import com.xectrone.agricultural_ai_assistant.ui.theme.Dimen
import com.xectrone.agricultural_ai_assistant.ui.theme.LocalCustomColorPalette

@Composable
fun DiseaseDetectionApp(
    selectedImageUri: Uri? = null,
    detectionResults: List<DiseaseResult>? = null,
    onCaptureImageClick: () -> Unit,
    onSelectImageClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(LocalCustomColorPalette.current.background)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Dimen.Padding.p4),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(Dimen.Padding.p7))

        Text(
            text = "Agricultural AI Assistant ",
            style = MaterialTheme.typography.h3,
            color = LocalCustomColorPalette.current.primary,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(Dimen.Padding.p4))

        Text(
            text = "Upload or capture an image to get disease plant detection results",
            color = LocalCustomColorPalette.current.secondary,
            textAlign = TextAlign.Center,
            style = CustomTypography.titleSecondary,
            fontWeight = FontWeight.Normal,
            lineHeight = 25.sp
        )

        Spacer(modifier = Modifier.height(Dimen.Padding.p7))

        Button(
            onClick = onSelectImageClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = LocalCustomColorPalette.current.primary,
                contentColor = LocalCustomColorPalette.current.background
            )
        ) {
            Text(" UPLOAD IMAGE ")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onCaptureImageClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = LocalCustomColorPalette.current.primary,
                contentColor = LocalCustomColorPalette.current.background
            )
        ) {
            Text("CAPTURE IMAGE")
        }

        Spacer(modifier = Modifier.height(Dimen.Padding.p7))

        selectedImageUri?.let { uri ->
            AsyncImage(
                model = uri,
                contentDescription = null,
                modifier = Modifier
                    .border(
                        1.dp,
                        color = LocalCustomColorPalette.current.tertiary,
                        shape = RoundedCornerShape(Dimen.Padding.p3)
                    )
                    .clip(RoundedCornerShape(8.dp))
                    .size(200.dp),
                placeholder = null,
                error = null,
                alpha = 1f,
                filterQuality = FilterQuality.None
            )
        }

        Spacer(modifier = Modifier.height(Dimen.Padding.p7))

        detectionResults?.let { results ->
            results.forEach { result ->
                ResponseItems(result = result)
            }
        }
    }
}