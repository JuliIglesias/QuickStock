package com.example.quickStock.recipe

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.LocalGroceryStore
import androidx.compose.material.icons.filled.SetMeal
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.quickStock.R
import com.example.quickStock.common.CustomGrid

@Composable
fun GridRecipes(
    modifier: Modifier = Modifier,
    onRecipeClick: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val typeOfRecipes = context.resources.getStringArray(R.array.recipe_types)
    val typeOfRecipesIcons = mapOf(
        "Meat" to Icons.Default.LocalDining,
        "Chicken" to Icons.Default.SetMeal,
        "Fish" to Icons.Default.SetMeal,
        "Vegetarian" to Icons.Default.LocalFlorist,
        "Vegan" to Icons.Default.LocalFlorist,
        "Desserts" to Icons.Default.Cake,
        "Alcoholic Beverages" to Icons.Default.LocalDrink,
        "Non-Alcoholic Beverages" to Icons.Default.LocalDrink,
        "Salads" to Icons.Default.LocalFlorist,
        "Soups" to Icons.Default.LocalDining,
        "Pasta" to Icons.Default.LocalDining,
        "Bakery" to Icons.Default.Cake,
        "Seafood" to Icons.Default.SetMeal,
        "Appetizers" to Icons.Default.LocalGroceryStore,
        "Sauces" to Icons.Default.LocalGroceryStore,
        "Grains and Legumes" to Icons.Default.LocalDining,
        "Breakfast" to Icons.Default.LocalDining,
        "Fast Food" to Icons.Default.LocalDining,
        "International Cuisine" to Icons.Default.LocalDining,
        "Main Dishes" to Icons.Default.LocalDining,
        "Starters" to Icons.Default.LocalDining,
        "Side Dishes" to Icons.Default.LocalDining
    )

    val buttonDataList = typeOfRecipes.map { recipeString ->
        RecipeButtonData(
            title = recipeString,
            icon = typeOfRecipesIcons[recipeString] ?: Icons.Default.Favorite,
            onClick = { onRecipeClick(recipeString) }
        )
    }

    CustomGrid(
        items = buttonDataList,
        modifier = modifier,
        columns = 2,
        verticalSpacing = 16,
        horizontalSpacing = 16
    )
}
