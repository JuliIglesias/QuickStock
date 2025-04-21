package com.example.quickStock.screensUI.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quickStock.screensUI.addProducts.AddProductSurvey
import com.example.quickStock.screensUI.home.HomeScreen
import com.example.quickStock.screensUI.home.stock.ProductDetailScreen
import com.example.quickStock.screensUI.home.stock.StockListScreen
import com.example.quickStock.mocking.getProductsByCategory
import com.example.quickStock.mocking.getRecipesByType
import com.example.quickStock.model.navigation.NavBarNames
import com.example.quickStock.screensUI.navigation.categories.CategoryRoutes
import com.example.quickStock.screensUI.navigation.categories.RecipeRoutes
import com.example.quickStock.screensUI.recipeScreens.RecipeScreen
import com.example.quickStock.screensUI.recipeScreens.recipe.RecipeDetailScreen
import com.example.quickStock.screensUI.recipeScreens.recipe.RecipeListScreen
import com.example.quickStock.screensUI.userConfig.UserSettingsPagePreview

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavHostComposable(innerPadding: PaddingValues, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavBarNames.Home.name,
        modifier = Modifier.fillMaxSize().padding(innerPadding).padding(20.dp)
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
        CategoryRoutes.entries.forEach { category ->
            composable(category.route) {

                StockListScreen(category = category.name,
                    onGoBack = {
                        navController.popBackStack()
                    },
                    onClick = { productId ->
                        navController.navigate("${category.route}/product/$productId")
                    }
                )
            }
        }

        composable("category/{categoryType}/product/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            val categoryType = backStackEntry.arguments?.getString("categoryType")
            val product = getProductsByCategory(categoryType?.replaceFirstChar { it.uppercase() } ?: "").find { it.id == productId }
            if (product != null) {
                ProductDetailScreen(
                    product = product,
                    onGoBack = { navController.popBackStack() }
                )
            }
        }

        // Recipe navigation
        RecipeRoutes.entries.forEach { recipe ->
            composable(recipe.route) {
                RecipeListScreen(recipeType = recipe.name,
                    onGoBack = {
                        navController.popBackStack()
                    },
                    onClick = { recipeId ->
                        navController.navigate("recipe/${recipe.name.lowercase()}/detail/$recipeId")
                    }
                )
            }
        }

        composable("recipe/{recipeType}/detail/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId")
            val recipeType = backStackEntry.arguments?.getString("recipeType")
            val recipe = getRecipesByType(recipeType?.replaceFirstChar { it.uppercase() } ?: "").find { it.id == recipeId }
            if (recipe != null) {
                RecipeDetailScreen(
                    recipe = recipe,
                    onGoBack = { navController.popBackStack() }
                )
            }
        }
    }
}