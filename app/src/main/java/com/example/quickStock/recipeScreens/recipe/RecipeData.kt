package com.example.quickStock.recipeScreens.recipe

data class RecipeData(
    val id: String,
    val name: String,
    val ingredients: List<String>,
    val preparationTime: String,
    val cookingTime: String? = null,
    val steps: List<String>
)