package com.example.quickStock.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = DarkGreen1,
    secondary = DarkGreen2,
    tertiary = DarkGreen3,

    background = DarkGreenBack,
    surface = DarkGreenBack,
    onPrimary = LightDarkText,
    onSecondary = LightDarkText,
    onTertiary = LightDarkText,
)

private val LightColorScheme = lightColorScheme(
    primary = LightGreen1,
    secondary = LightGreen2,
    tertiary = LightGreen3,


    background = LightGreenBack,
    surface = LightGreenBack,
    onPrimary = LightDarkText,
    onSecondary = LightDarkText,
    onTertiary = LightDarkText,


)

@Composable
fun QuickStockTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}