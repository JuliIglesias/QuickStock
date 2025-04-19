package com.example.quickStock.screensUI.recipeScreens.recipe

import androidx.compose.ui.Modifier

data class RecipeButtonData(
    val title: String,
    val image: String?,
    val onClick: () -> Unit,
    val modifier: Modifier = Modifier
)