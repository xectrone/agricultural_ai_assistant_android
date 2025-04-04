package com.xectrone.agricultural_ai_assistant.ui.home_screen


import android.net.Uri
import android.util.Log
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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
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
    onBack: () -> Unit
) {


    Column(
        modifier = Modifier
            .background(LocalCustomColorPalette.current.background)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Dimen.Padding.p5),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        if (!search){
            Image(
                modifier = Modifier.padding(top = 85.dp),
                painter = painterResource(id = R.drawable.boy),
                contentDescription = ""

            )


            Text(
                modifier = Modifier,
                text = "Agricultural AI Assistant ",
                style = MaterialTheme.typography.h3,
                color = Green1,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = Rubic,

                )

            Spacer(modifier = Modifier.height(28.dp))



            Text(
                modifier = Modifier,
                text = "Upload or capture an image to get disease plant detection results",
                color = Green2,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                fontFamily = Rubic,
            )



            Row(
                modifier = Modifier.padding(top = 38.dp)
            ) {

                IconButton(
                    modifier = Modifier
                        .height(47.dp)
                        .width(101.dp)
                        .background(Green1, RoundedCornerShape(13.dp))
                    ,
                    onClick = {
                        onSelectImageClick()

                    }
                ) {
                    Icon(painterResource(id =  R.drawable.round_upload_24), contentDescription = "", tint = Color.White)
                }

                Spacer(modifier = Modifier.width(25.dp))

                IconButton(
                    modifier = Modifier
                        .height(47.dp)
                        .width(101.dp)
                        .background(Green1, RoundedCornerShape(13.dp)),
                    onClick = {
                        onCaptureImageClick()
                    }
                ) {
                    Icon(painterResource(id =  R.drawable.round_photo_camera_24), contentDescription = "", tint = Color.White)
                }
            }
        }
        else{

            Spacer(modifier = Modifier.height(30.dp))
            IconButton(
                modifier = Modifier
                    .height(47.dp)
                    .width(101.dp)
                    .background(Green1, RoundedCornerShape(13.dp))
                    .align(Alignment.Start),
                onClick = {
                    onBack()
                }
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

//            ResponseItems(test)

            detectionResults?.let { results ->
                ResponseItems(result = results.first())
            }

        }
    }
}