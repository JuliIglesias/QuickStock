package com.example.quickStock.screensUI.home.stock

import androidx.compose.ui.Modifier

data class StockButtonData(
    val title: String,
    val quantity: Int,
    val onClick: () -> Unit,
    val modifier: Modifier = Modifier
)