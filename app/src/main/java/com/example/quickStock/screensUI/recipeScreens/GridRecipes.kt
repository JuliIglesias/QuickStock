package com.example.quickStock.screensUI.recipeScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quickStock.R
import com.example.quickStock.model.recipe.RecipeButtonData
import com.example.quickStock.screensUI.common.SimpleSearchBar
import com.example.quickStock.screensUI.common.principal.CustomGrid
import com.example.quickStock.screensUI.icon.getRecipesTypesIcon
import com.example.quickStock.screensUI.navigation.categories.RecipeRoutes
import com.example.quickStock.ui.theme.paddingLarge
import com.example.quickStock.ui.theme.spacingExtraLarge
import com.example.quickStock.viewModel.recipeScreens.GridRecipesViewModel

@Composable
fun GridRecipes(
    modifier: Modifier = Modifier,
    onRecipeClick: (String) -> Unit = {}
) {
    val viewModel = hiltViewModel<GridRecipesViewModel>()

    val context = LocalContext.current
    val typeOfRecipes = context.resources.getStringArray(R.array.recipe_types)

    val searchQuery by viewModel.searchQuery.collectAsState()
    val filteredRecipes by viewModel.filteredRecipes.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()

    val buttonDataList = typeOfRecipes.map { recipeString ->
        val route = RecipeRoutes.entries.find { it.name.equals(recipeString, ignoreCase = true) }?.route
        RecipeButtonData(
            title = recipeString,
            icon = getRecipesTypesIcon(recipeString),
            onClick = {
                if (route != null) {
                    onRecipeClick(route)
                }
            }
        )
    }

    viewModel.setRecipes(buttonDataList)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = paddingLarge),
        verticalArrangement = Arrangement.Top,
    ) {
        SimpleSearchBar(
            query = searchQuery,
            onQueryChange = { newQuery ->
                viewModel.updateSearchQuery(newQuery)
            },
            onSearch = {
                viewModel.performSearch()
            },
            searchResults = searchResults,
            modifier = Modifier
        )

        CustomGrid(
            items = filteredRecipes,
            modifier = modifier,
            columns = 2,
            verticalSpacing = spacingExtraLarge,
            horizontalSpacing = spacingExtraLarge
        )
    }
}