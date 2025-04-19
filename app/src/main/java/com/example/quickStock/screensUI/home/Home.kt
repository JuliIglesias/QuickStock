package com.example.quickStock.screensUI.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface


@Composable
fun HomeScreen(onClick: (String) -> Unit,) {
    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
        ) {
            ProductCategoryGrid(onCategoryClick = onClick)
        }
    }
}


