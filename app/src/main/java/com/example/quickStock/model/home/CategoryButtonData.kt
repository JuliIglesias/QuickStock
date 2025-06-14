package com.example.quickStock.model.home

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.quickStock.model.common.ICardButton

data class CategoryButtonData(
    override val title: String,
    override val icon: ImageVector,
    override val onClick: () -> Unit,
    override val modifier: Modifier = Modifier,
    val containerColor: Color? = null
) : ICardButton

