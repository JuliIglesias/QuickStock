package com.example.quickStock.screensUI.common.goBack

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.quickStock.R
import com.example.quickStock.ui.theme.paddingMedium

@Composable
fun GoBackButton(onGoBack: () -> Unit) {
    Icon(
        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
        contentDescription = stringResource(R.string.action_go_back),
        modifier = Modifier
            .clickable { onGoBack() }
            .padding(paddingMedium),
        tint = MaterialTheme.colorScheme.onSecondary
    )
}