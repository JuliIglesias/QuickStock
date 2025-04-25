package com.example.quickStock.screensUI.common.goBack

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.quickStock.ui.theme.paddingExtraLarge
import com.example.quickStock.ui.theme.textSizeExtraLarge

@Composable
fun ScreenName(onGoBack: () -> Unit, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GoBackButton {
            onGoBack()
        }

        Text(
            text = title,
            fontSize = textSizeExtraLarge,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}