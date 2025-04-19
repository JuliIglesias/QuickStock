package com.example.quickStock.model.recipe

data class RecipeData(
    val id: String,
    val name: String,
    val ingredients: List<String>,
    val measurements: List<String>,
    val image: String? = null,
    val steps: List<String>,
    val youtubeUrl: String? = null
)