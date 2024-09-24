package com.xectrone.agricultural_ai_assistant.ui.home_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.xectrone.agricultural_ai_assistant.ui.theme.LocalCustomColorPalette

@Composable
fun ResponseItems(result: DiseaseResult) {
    Column(modifier = Modifier
        .border(
            1.dp,
            LocalCustomColorPalette.current.tertiary,
            RoundedCornerShape(Dimen.Padding.p3)
        )
        .padding(Dimen.Padding.p3),) {
        Text("➤ Detected ${result.label} with confidence of ${result.confidence}.", style = CustomTypography.titleSecondary, color = LocalCustomColorPalette.current.primary)
        Spacer(modifier = Modifier.height(Dimen.Padding.p3))

        Text(text = "●  Description", style = CustomTypography.body, fontWeight = FontWeight.Bold, color = LocalCustomColorPalette.current.primary)
        Spacer(modifier = Modifier.height(Dimen.Padding.p1))
        Text("${result.description}",style = CustomTypography.body, color = LocalCustomColorPalette.current.primary)
        Spacer(modifier = Modifier.height(Dimen.Padding.p2))

        Text(text = "●  Cause", style = CustomTypography.body, fontWeight = FontWeight.Bold, color = LocalCustomColorPalette.current.primary)
        Text("${result.cause}",style = CustomTypography.body, color = LocalCustomColorPalette.current.primary)
        Spacer(modifier = Modifier.height(Dimen.Padding.p2))

        Text(text = "●  Treatment", style = CustomTypography.body, fontWeight = FontWeight.Bold, color = LocalCustomColorPalette.current.primary)
        Text("${result.treatment}",style = CustomTypography.body, color = LocalCustomColorPalette.current.primary)
        Spacer(modifier = Modifier.height(Dimen.Padding.p2))

        Text(text = "●  Prevention", style = CustomTypography.body, fontWeight = FontWeight.Bold, color = LocalCustomColorPalette.current.primary)
        Text("${result.prevention}",style = CustomTypography.body, color = LocalCustomColorPalette.current.primary)
        Spacer(modifier = Modifier.height(Dimen.Padding.p2))



    }
    Spacer(modifier = Modifier.height(Dimen.Padding.p4))
}