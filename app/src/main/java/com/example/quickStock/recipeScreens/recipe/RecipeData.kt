package com.example.quickStock.recipeScreens.recipe

data class RecipeData(
    val id: String,
    val name: String,
    val ingredients: List<String>,
    val image: String? = null,
    val steps: List<String>,
    val youtubeUrl: String? = null
)