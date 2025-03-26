package com.example.quickStock.icon

import androidx.compose.foundation.Image
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector


sealed class IconType {
    data class Vector(val imageVector: ImageVector) : IconType()
    data class Drawable(val painter: Painter) : IconType()
}

@Composable
fun MyIcon(
    icon: IconType,
    contentDescription: String? = null
) {
    when (icon) {
        is IconType.Vector -> Icon(
            imageVector = icon.imageVector,
            contentDescription = contentDescription
        )
        is IconType.Drawable -> Image(
            painter = icon.painter,
            contentDescription = contentDescription
        )
    }
}

