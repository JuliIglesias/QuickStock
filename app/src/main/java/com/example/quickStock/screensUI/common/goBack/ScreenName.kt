package com.example.quickStock.screensUI.common.goBack

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier

@Composable
fun ScreenName(onGoBack: () -> Unit, title:String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GoBackButton {
            onGoBack()
        }

        Text(
            text = title,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}