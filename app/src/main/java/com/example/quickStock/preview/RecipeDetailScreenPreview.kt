package com.example.quickStock.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.quickStock.model.recipe.RecipeData
import com.example.quickStock.screensUI.recipeScreens.recipe.RecipeDetailScreen
import com.example.quickStock.ui.theme.QuickStockTheme


@Preview(showBackground = true)
@Composable
fun RecipeDetailScreenPreview () {
    QuickStockTheme {
        RecipeDetailScreen(
            recipeId = "52890",
            onGoBack = { /* Handle back navigation */ },
        )
    }
}
