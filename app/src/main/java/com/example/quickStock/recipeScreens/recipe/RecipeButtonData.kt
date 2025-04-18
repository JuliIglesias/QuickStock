package com.example.quickStock.recipeScreens.recipe

import androidx.compose.ui.Modifier

data class RecipeButtonData(
    val title: String,
    val time: Int,
    val onClick: () -> Unit,
    val modifier: Modifier = Modifier
)