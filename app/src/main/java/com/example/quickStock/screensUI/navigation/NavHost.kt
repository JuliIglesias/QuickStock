package com.example.quickStock.screensUI.navigation

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quickStock.screensUI.addProducts.AddProductSurvey
import com.example.quickStock.screensUI.home.HomeScreen
import com.example.quickStock.screensUI.home.stock.ProductDetailScreen
import com.example.quickStock.screensUI.home.stock.StockListScreen
import com.example.quickStock.mocking.getProductsByCategory
import com.example.quickStock.model.navigation.NavBarNames
import com.example.quickStock.screensUI.recipeScreens.RecipeScreen
import com.example.quickStock.screensUI.recipeScreens.recipe.RecipeDetailScreen
import com.example.quickStock.screensUI.recipeScreens.recipe.RecipeListScreen
import com.example.quickStock.screensUI.userConfig.UserSettingsPagePreview
import com.example.quickStock.ui.theme.paddingLarge

@Composable
fun NavHostComposable(innerPadding: PaddingValues, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavBarNames.Home.name,
        modifier = Modifier.fillMaxSize().padding(innerPadding).padding(paddingLarge)
    ) {

        //NavBar navigation
        composable(route = NavBarNames.Home.name) {
            HomeScreen(
                onClick = { navController.navigate(it) }
            )
        }

        composable(route = NavBarNames.Recipe.name) {
            RecipeScreen(
                onClick = { navController.navigate(it) }
            )
        }

        composable(route = NavBarNames.AddProduct.name) {
            AddProductSurvey(
                onClick = { navController.navigate(it) }
            )
        }

        composable(route = NavBarNames.User.name) {
            UserSettingsPagePreview(
                 onClick = { navController.navigate(it) }
            )
        }

        // Category navigation
        composable("category/{categoryType}") { backStackEntry ->
            val categoryType = backStackEntry.arguments?.getString("categoryType")

            StockListScreen(category = categoryType?.replaceFirstChar { it.uppercase() } ?: "",
                onGoBack = {
                    navController.popBackStack()
                },
                onClick = { productId ->
                    navController.navigate("category/$categoryType/detail/product/$productId")
                }
            )
        }

        composable("category/{categoryType}/detail/product/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            ProductDetailScreen(
                productId = productId ?: "",
                onGoBack = { navController.popBackStack() }
            )
        }

        // Recipe navigation
        composable("recipe/{categoryId}/{categoryName}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")
            val categoryName = backStackEntry.arguments?.getString("categoryName")

            // Ahora pasamos el categoryId y categoryName a la lista de recetas
            if (categoryId != null && categoryName != null) {
                RecipeListScreen(
                    categoryId = categoryId.toInt(),
                    recipeType = categoryName,
                    onGoBack = {
                        navController.popBackStack()
                    },
                    onClick = { recipeId ->
                        navController.navigate("recipe/$categoryId/$categoryName/detail/$recipeId")
                    }
                )
            }
        }

        composable("recipe/{categoryId}/{categoryName}/detail/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId")

            if (recipeId != null) {
                RecipeDetailScreen(
                    recipeId = recipeId,
                    onGoBack = { navController.popBackStack() }
                )
            }
        }
    }
}

