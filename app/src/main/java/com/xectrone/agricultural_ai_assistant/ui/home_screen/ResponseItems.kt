package com.xectrone.agricultural_ai_assistant.ui.home_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.xectrone.agricultural_ai_assistant.ui.theme.CustomTypography
import com.xectrone.agricultural_ai_assistant.ui.theme.Dimen
import com.xectrone.agricultural_ai_assistant.ui.theme.Green1
import com.xectrone.agricultural_ai_assistant.ui.theme.Green2
import com.xectrone.agricultural_ai_assistant.ui.theme.LocalCustomColorPalette
import com.xectrone.agricultural_ai_assistant.ui.theme.Rubic

@Composable
fun ResponseItems(result: DiseaseResult) {
    Column(
        modifier =  Modifier.fillMaxWidth()
    ) {
        Text("${result.label} (${result.confidence})",
            style = CustomTypography.title,
            color = Green1,
            fontFamily = Rubic,
            fontWeight = FontWeight.SemiBold)


        Spacer(modifier = Modifier.height(Dimen.Padding.p5))

        Text(text = "Description",
            style = CustomTypography.titleSecondary,
            color = Green1,
            fontFamily = Rubic,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(Dimen.Padding.p3))
        Text("${result.description}",
            style = CustomTypography.body,
            color = Green2,
            fontFamily = Rubic
        )
        Spacer(modifier = Modifier.height(Dimen.Padding.p5))


        Text(text = "Cause",
            style = CustomTypography.titleSecondary,
            color = Green1,
            fontFamily = Rubic,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(Dimen.Padding.p3))
        Text("${result.cause}",
            style = CustomTypography.body,
            color = Green2,
            fontFamily = Rubic
        )
        Spacer(modifier = Modifier.height(Dimen.Padding.p5))



        Text(text = "Treatment",
            style = CustomTypography.titleSecondary,
            color = Green1,
            fontFamily = Rubic,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(Dimen.Padding.p3))
        Text("${result.treatment}",
            style = CustomTypography.body,
            color = Green2,
            fontFamily = Rubic
        )
        Spacer(modifier = Modifier.height(Dimen.Padding.p5))



        Text(text = "Prevention",
            style = CustomTypography.titleSecondary,
            color = Green1,
            fontFamily = Rubic,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(Dimen.Padding.p3))
        Text("${result.prevention}",
            style = CustomTypography.body,
            color = Green2,
            fontFamily = Rubic
        )
        Spacer(modifier = Modifier.height(Dimen.Padding.p5))


    }


}