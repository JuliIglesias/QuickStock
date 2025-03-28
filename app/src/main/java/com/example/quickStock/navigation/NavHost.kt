package com.example.quickStock.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quickStock.addProducts.AddProductSurvey
import com.example.quickStock.home.HomeScreen
import com.example.quickStock.recipe.RecipeScreen
import com.example.quickStock.userConfig.UserSettingsPagePreview




@Composable
fun NavHostComposable(innerPadding: PaddingValues, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavBarNames.Home.name,
        modifier = Modifier.fillMaxSize().padding(innerPadding).padding(20.dp)
    ) {

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

    }
}