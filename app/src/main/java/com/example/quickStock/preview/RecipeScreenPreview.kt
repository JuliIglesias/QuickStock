package com.example.quickStock.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.quickStock.model.addProduct.Product
import com.example.quickStock.model.addProduct.QuantityExpirationDate
import com.example.quickStock.screensUI.home.stock.ProductDetailScreen
import com.example.quickStock.screensUI.recipeScreens.GridRecipes
import com.example.quickStock.screensUI.recipeScreens.RecipeScreen
import com.example.quickStock.ui.theme.QuickStockTheme

@Preview(showBackground = true)
@Composable
fun RecipeScreenPreview () {
    QuickStockTheme {
        GridRecipes(
            onNavigateToCategory = { categoryId, categoryName ->
                // Handle navigation here
            }
        )
    }
}