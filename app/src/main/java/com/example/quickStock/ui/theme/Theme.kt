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
import com.example.quickStock.screensUI.userConfig.DarkModeConfig

private val DarkColorScheme = darkColorScheme(
    primary = DarkGreen3,
    secondary = DarkGreen2,
    tertiary = DarkGreen1,

    background = DarkGreenBack,
    surface = DarkGreenBack,
    surfaceVariant = DarkCardBackground,
    onPrimary = DarkDarkText,
    onSecondary = DarkLightText,
    onTertiary = DarkDarkText,
    onSurfaceVariant = DarkLightText,

    // New colors
    outlineVariant = DarkDetailBackground
)

private val LightColorScheme = lightColorScheme(
    primary = LightGreen1,
    secondary = LightGreen2,
    tertiary = LightGreen3,

    background = LightGreenBack,
    surface = LightGreenBack,
    surfaceVariant = LightCardBackground,
    onPrimary = LightDarkText,
    onSecondary = LightDarkText,
    onTertiary = LightDarkText,
    onSurfaceVariant = LightDarkText,

    // New colors
    outlineVariant = LightDetailBackground
)

@Composable
fun QuickStockTheme(
    content: @Composable () -> Unit
) {
    val systemDarkTheme = isSystemInDarkTheme()
    val darkTheme = DarkModeConfig.darkModeEnabled ?: systemDarkTheme

    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}