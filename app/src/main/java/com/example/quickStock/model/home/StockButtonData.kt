package com.example.quickStock.model.home

import androidx.compose.ui.Modifier

data class StockButtonData(
    val title: String,
    val quantity: Int,
    val onClick: () -> Unit,
    val modifier: Modifier = Modifier
)