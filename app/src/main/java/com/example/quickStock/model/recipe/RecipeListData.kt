package com.example.quickStock.model.recipe

import androidx.compose.ui.Modifier

data class RecipeListData(
    val idMeal: String,
    val title: String,
    val image: String?,
    val onClick: () -> Unit,
    val modifier: Modifier = Modifier
)