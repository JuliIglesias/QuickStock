package com.example.quickStock.mocking

import com.example.quickStock.R
import com.example.quickStock.recipeScreens.recipe.RecipeData

fun getRecipesByType(type: String): List<RecipeData> {
    return when (type) {
        "Meat" -> listOf(
            RecipeData(
                id = "1",
                name = "Grilled Steak",
                ingredients = listOf("Steak", "Salt", "Pepper", "Olive Oil"),
                measurements = listOf("1 lb", "to taste", "to taste", "2 tbsp"),
                steps = listOf("Season the steak", "Heat the grill", "Cook the steak for 7 minutes per side")
            ),
            RecipeData(
                id = "2",
                name = "Beef Stroganoff",
                ingredients = listOf("Beef", "Mushrooms", "Onion", "Sour Cream"),
                measurements = listOf("1 lb", "8 oz", "1", "1 cup"),
                steps = listOf("Slice the beef", "Cook the onions and mushrooms", "Add sour cream and simmer")
            ),
            RecipeData(
                id = "3",
                name = "Meatballs",
                ingredients = listOf("Ground Beef", "Breadcrumbs", "Egg", "Garlic"),
                measurements = listOf("1 lb", "1 cup", "1", "2 cloves"),
                steps = listOf("Mix ingredients", "Form meatballs", "Cook in sauce"),
                youtubeUrl = "https://www.youtube.com/watch?v=IUIs4C86Ilo"
            )
        )
        "Seafood" -> listOf(
            RecipeData(
                id = "4",
                name = "Grilled Salmon",
                ingredients = listOf("Salmon", "Lemon", "Garlic", "Dill"),
                measurements = listOf("1 lb", "1", "2 cloves", "1 tbsp"),
                steps = listOf("Season the salmon", "Preheat the grill", "Grill the salmon for 10 minutes per side")
            ),
            RecipeData(
                id = "5",
                name = "Shrimp Scampi",
                ingredients = listOf("Shrimp", "Garlic", "Butter", "Parsley"),
                measurements = listOf("1 lb", "4 cloves", "4 tbsp", "2 tbsp"),
                steps = listOf("Cook garlic in butter", "Add shrimp and cook until pink", "Garnish with parsley")
            ),
            RecipeData(
                id = "6",
                name = "Tuna Salad",
                ingredients = listOf("Tuna", "Mayonnaise", "Celery", "Lemon Juice"),
                measurements = listOf("1 can", "1/4 cup", "1 stalk", "1 tbsp"),
                steps = listOf("Mix all ingredients", "Serve chilled")
            )
        )
        // Agregar más tipos de recetas aquí
        else -> emptyList()
    }
}