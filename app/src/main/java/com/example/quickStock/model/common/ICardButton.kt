package com.example.quickStock.model.common

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

interface ICardButton {
    val title: String
    val icon: ImageVector
    val onClick: () -> Unit
    val modifier: Modifier
}