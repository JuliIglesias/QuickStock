package com.example.quickStock.mocking

import com.example.quickStock.recipeScreens.recipe.RecipeData

fun getRecipesByType(type: String): List<RecipeData> {
    return when (type) {
        "Meat" -> listOf(
            RecipeData(
                id = "1",
                name = "Grilled Steak",
                ingredients = listOf("Steak", "Salt", "Pepper", "Olive Oil"),
                preparationTime = "10 min",
                cookingTime = "15 min",
                steps = listOf("Season the steak", "Heat the grill", "Cook the steak for 7 minutes per side")
            ),
            RecipeData(
                id = "2",
                name = "Beef Stroganoff",
                ingredients = listOf("Beef", "Mushrooms", "Onion", "Sour Cream"),
                preparationTime = "15 min",
                cookingTime = "30 min",
                steps = listOf("Slice the beef", "Cook the onions and mushrooms", "Add sour cream and simmer")
            ),
            RecipeData(
                id = "3",
                name = "Meatballs",
                ingredients = listOf("Ground Beef", "Breadcrumbs", "Egg", "Garlic"),
                preparationTime = "20 min",
                cookingTime = "25 min",
                steps = listOf("Mix ingredients", "Form meatballs", "Cook in sauce")
            )
        )
        "Seafood" -> listOf(
            RecipeData(
                id = "4",
                name = "Grilled Salmon",
                ingredients = listOf("Salmon", "Lemon", "Garlic", "Dill"),
                preparationTime = "10 min",
                cookingTime = "20 min",
                steps = listOf("Season the salmon", "Preheat the grill", "Grill the salmon for 10 minutes per side")
            ),
            RecipeData(
                id = "5",
                name = "Shrimp Scampi",
                ingredients = listOf("Shrimp", "Garlic", "Butter", "Parsley"),
                preparationTime = "10 min",
                cookingTime = "15 min",
                steps = listOf("Cook garlic in butter", "Add shrimp and cook until pink", "Garnish with parsley")
            ),
            RecipeData(
                id = "6",
                name = "Tuna Salad",
                ingredients = listOf("Tuna", "Mayonnaise", "Celery", "Lemon Juice"),
                preparationTime = "10 min",
                steps = listOf("Mix all ingredients", "Serve chilled")
            )
        )
        // Agregar más tipos de recetas aquí
        else -> emptyList()
    }
}