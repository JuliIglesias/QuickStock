package com.example.quickStock.screensUI.common.principal

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ModernTextButton(
    text: String,
    textColor: Color,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    TextButton(
        onClick = onClick,
        interactionSource = interactionSource,
        colors = ButtonDefaults.textButtonColors(
            contentColor = textColor,
            // Color cuando se presiona - un tono más claro con alfa reducido
            disabledContentColor = textColor.copy(alpha = 0.5f)
        ),
        modifier = Modifier
            .padding(8.dp)
            .background(
                color = if (isPressed) {
                    textColor.copy(alpha = 0.1f) // Fondo sutil cuando se presiona
                } else {
                    Color.Transparent
                },
                shape = CircleShape
            )
    ) {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.labelLarge
        )
    }
}