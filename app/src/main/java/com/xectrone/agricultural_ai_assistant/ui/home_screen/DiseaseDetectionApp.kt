package com.xectrone.agricultural_ai_assistant.ui.home_screen


import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.xectrone.agricultural_ai_assistant.R
import com.xectrone.agricultural_ai_assistant.ui.theme.CustomTypography
import com.xectrone.agricultural_ai_assistant.ui.theme.Dimen
import com.xectrone.agricultural_ai_assistant.ui.theme.Green1
import com.xectrone.agricultural_ai_assistant.ui.theme.Green2
import com.xectrone.agricultural_ai_assistant.ui.theme.LocalCustomColorPalette
import com.xectrone.agricultural_ai_assistant.ui.theme.Rubic

@Composable
fun DiseaseDetectionApp(
    selectedImageUri: Uri? = null,
    detectionResults: List<DiseaseResult>? = null,
    onCaptureImageClick: () -> Unit,
    onSelectImageClick: () -> Unit,
    search: Boolean,
    selectedLanguage: String,
    loading: Boolean,
    onLanguageSelected: (String) -> Unit,
    onBack: () -> Unit
) {

    var showDialog by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .background(LocalCustomColorPalette.current.background)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Dimen.Padding.p5),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {



        if (!search) {
            IconButton(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 16.dp, start = 8.dp),
                onClick = { showDialog = true }
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Info",
                    tint = Green2
                )
            }


            Image(
                modifier = Modifier.padding(top = 65.dp),
                painter = painterResource(id = R.drawable.boy),
                contentDescription = ""
            )

            Text(
                text = "Agricultural AI Assistant ",
                style = MaterialTheme.typography.h3,
                color = Green1,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = Rubic,
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Upload or capture an image to get disease plant detection results",
                color = Green2,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                fontFamily = Rubic,
            )

            // 🟡 Language Dropdown Menu
            Spacer(modifier = Modifier.height(20.dp))

            var expanded by remember { mutableStateOf(false) }
            val languages = listOf("English" to "en", "Hindi" to "hi", "Marathi" to "mr")
            val selected = languages.find { it.second == selectedLanguage }?.first ?: "Select Language"

            Box {
                OutlinedButton(
                    border = BorderStroke(width = 1.dp, color = Green2),
                    onClick = { expanded = true }
                ) {
                    Text(text = selected, fontFamily = Rubic, color = Green2)
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    languages.forEach { (name, code) ->
                        DropdownMenuItem(onClick = {
                            onLanguageSelected(code)
                            expanded = false
                        }) {
                            Text(text = name)
                        }
                    }
                }
            }

            // 🔽 Buttons Row
            Row(
                modifier = Modifier.padding(top = 38.dp)
            ) {
                IconButton(
                    modifier = Modifier
                        .height(47.dp)
                        .width(101.dp)
                        .background(Green1, RoundedCornerShape(13.dp)),
                    onClick = { onSelectImageClick() }
                ) {
                    Icon(painterResource(id = R.drawable.round_upload_24), contentDescription = "", tint = Color.White)
                }

                Spacer(modifier = Modifier.width(25.dp))

                IconButton(
                    modifier = Modifier
                        .height(47.dp)
                        .width(101.dp)
                        .background(Green1, RoundedCornerShape(13.dp)),
                    onClick = { onCaptureImageClick() }
                ) {
                    Icon(painterResource(id = R.drawable.round_photo_camera_24), contentDescription = "", tint = Color.White)
                }
            }
        } else {
            Spacer(modifier = Modifier.height(30.dp))
            IconButton(
                modifier = Modifier
                    .height(47.dp)
                    .width(101.dp)
                    .background(Green1, RoundedCornerShape(13.dp))
                    .align(Alignment.Start),
                onClick = { onBack() }
            ) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "", tint = Color.White)
            }

            Spacer(modifier = Modifier.height(30.dp))

            selectedImageUri?.let { uri ->
                AsyncImage(
                    model = uri,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth(),
                    placeholder = null,
                    error = null,
                    alpha = 1f,
                    filterQuality = FilterQuality.None,
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(Dimen.Padding.p7))

            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .size(50.dp),
                    color = Green1,
                    strokeWidth = 4.dp
                )
            } else {
                detectionResults?.let { results ->
                    ResponseItems(result = results.first())
                }
            }

        }
    }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colors.surface,
                elevation = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Agricultural AI Assistant",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    Text(
                        text = "You can use this by uploading or capturing the image of the plant and then the app will detect the disease and give you disease description, cause, treatment, and preventions.",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(text = "Plants", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "1. Apple\n2. Blueberry\n3. Cherry (including sour)\n4. Corn (maize)\n5. Grape\n6. Orange\n7. Peach\n8. Pepper, bell\n9. Potato\n10. Raspberry\n11. Soybean\n12. Squash\n13. Strawberry\n14. Tomato")

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "Diseases (and health status)", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = """
                        1. Apple scab
                        2. Black rot
                        3. Cedar apple rust
                        4. Powdery mildew
                        5. Cercospora leaf spot / Gray leaf spot
                        6. Common rust
                        7. Northern Leaf Blight
                        8. Black rot (Grape)
                        9. Esca (Black Measles)
                        10. Leaf blight (Isariopsis Leaf Spot)
                        11. Haunglongbing (Citrus greening)
                        12. Bacterial spot
                        13. Early blight
                        14. Late blight
                        15. Leaf scorch
                        16. Leaf Mold
                        17. Septoria leaf spot
                        18. Spider mites (Two-spotted spider mite)
                        19. Target Spot
                        20. Tomato Yellow Leaf Curl Virus
                        21. Tomato mosaic virus
                        22. Algal spot
                        23. Brown blight
                        24. Gray blight
                        25. Helopeltis
                        26. Red spot
                        27. Healthy
                    """.trimIndent()
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { showDialog = false },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("OK")
                    }
                }
            }
        }
    }
}
