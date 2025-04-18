package com.example.quickStock.home.stock

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.quickStock.common.ICardButton

data class StockButtonData(
    val title: String,
    val quantity: Int,
    val onClick: () -> Unit,
    val modifier: Modifier = Modifier
)