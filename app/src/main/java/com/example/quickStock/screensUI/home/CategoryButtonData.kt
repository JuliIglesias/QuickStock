package com.example.quickStock.screensUI.home

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.quickStock.screensUI.common.principal.ICardButton

data class CategoryButtonData(
    override val title: String,
    override val icon: ImageVector,
    override val onClick: () -> Unit,
    override val modifier: Modifier = Modifier
) : ICardButton