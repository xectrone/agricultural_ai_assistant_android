package com.xectrone.agricultural_ai_assistant.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
private val DarkColorPalette = darkColors(
    background = Color(0xFF000000),
    primary = Color(0xFFE9E9E9),
    primaryVariant = Color(0xFF8C8C8C),
    surface = Color(0xFF242424),
    secondary = Color(0xFF03A9F4),
)

private val LightColorPalette = lightColors(
    background = Color(0xFFEDEDED),
    primary = Color(0xFF191919),
    primaryVariant = Color(0xFF7F7F7F),
    surface = Color(0xFFF7F7F7),
    secondary = Color(0xFF03A9F4),

    )

@Composable
fun AgriculturalAIAssistantTheme(darkTheme: Boolean = false, content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val customColorsPalette =
        if (darkTheme) OnLightCustomColorPalette
        else OnLightCustomColorPalette

    CompositionLocalProvider(
        LocalCustomColorPalette provides customColorsPalette
    ){
        MaterialTheme(
            colors = colors,
            typography = Typography,
            content = content
        )
    }
}