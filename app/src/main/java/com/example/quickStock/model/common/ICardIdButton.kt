package com.example.quickStock.model.common

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

interface ICardIdButton {
    val title: String
    val icon: Int
    val onClick: () -> Unit
    val modifier: Modifier
}