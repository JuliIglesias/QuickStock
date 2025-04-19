package com.example.quickStock.model.recipe

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.quickStock.model.common.ICardButton

data class RecipeButtonData(
    override val title: String,
    override val icon: ImageVector,
    override val onClick: () -> Unit,
    override val modifier: Modifier = Modifier
) : ICardButton