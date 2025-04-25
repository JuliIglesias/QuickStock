package com.example.quickStock.screensUI.icon

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.quickStock.ui.theme.Unspecified


@Composable
fun MyIcon(
    icon: ImageVector,
    contentDescription: String? = null,
    modifier: Modifier,
    tint: Color= Unspecified,
) {
    Icon(
        imageVector = icon,
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

