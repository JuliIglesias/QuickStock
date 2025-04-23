package com.example.quickStock.screensUI.common.secondary

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.example.quickStock.ui.theme.elevationMedium
import com.example.quickStock.ui.theme.heightButton
import com.example.quickStock.ui.theme.radiusRound
import com.example.quickStock.ui.theme.spacingMedium

@Composable
fun ButtonIconAndName(
    onClick: () -> Unit,
    text: String,
    icon: ImageVector
) {

    Button(
    onClick = {
        onClick()
    },
    modifier = Modifier
    .fillMaxWidth()
    .height(heightButton),
    shape = RoundedCornerShape(radiusRound),
    colors = ButtonDefaults.buttonColors(
    containerColor = MaterialTheme.colorScheme.primary,
    ),
    elevation = ButtonDefaults.buttonElevation(defaultElevation = elevationMedium),

    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = Modifier.width(spacingMedium))
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}