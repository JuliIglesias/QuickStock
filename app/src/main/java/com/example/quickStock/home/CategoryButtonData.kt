package com.example.quickStock.home

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.quickStock.common.ICardButton

data class CategoryButtonData(
    override val title: String,
    override val icon: ImageVector,
    override val onClick: () -> Unit,
    override val modifier: Modifier = Modifier
) : ICardButton