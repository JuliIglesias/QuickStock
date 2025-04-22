package com.example.quickStock.screensUI.recipeScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quickStock.model.recipe.RecipeButtonData
import com.example.quickStock.screensUI.common.SimpleSearchBar
import com.example.quickStock.screensUI.common.principal.CustomGrid
import com.example.quickStock.ui.theme.paddingLarge
import com.example.quickStock.ui.theme.spacingExtraLarge
import com.example.quickStock.viewModel.recipeScreens.GridRecipesViewModel

@Composable
fun GridRecipes(
    modifier: Modifier = Modifier,
) {
    val viewModel = hiltViewModel<GridRecipesViewModel>()

    val searchQuery by viewModel.searchQuery.collectAsState()
    val filteredRecipes by viewModel.filteredRecipes.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()

    val adaptedItems = filteredRecipes.map { buttonIdData ->
        RecipeButtonData(
            title = buttonIdData.title,
            icon = ImageVector.vectorResource(id = buttonIdData.icon),
            onClick = buttonIdData.onClick,
            modifier = buttonIdData.modifier
        )
    }

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
            items = adaptedItems,
            modifier = modifier,
            columns = 2,
            verticalSpacing = spacingExtraLarge,
            horizontalSpacing = spacingExtraLarge
        )
    }
}