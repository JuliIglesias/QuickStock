package com.example.quickStock.screensUI.recipeScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quickStock.R
import com.example.quickStock.model.recipe.RecipeButtonData
import com.example.quickStock.screensUI.common.SimpleSearchBar
import com.example.quickStock.screensUI.common.principal.CustomGrid
import com.example.quickStock.ui.theme.paddingLarge
import com.example.quickStock.ui.theme.spacingExtraLarge
import com.example.quickStock.viewModel.recipeScreens.GridRecipesViewModel

@Composable
fun GridRecipes(
    modifier: Modifier = Modifier,
    onNavigateToCategory: (String, String) -> Unit
) {
    val viewModel = hiltViewModel<GridRecipesViewModel>()

    val searchQuery by viewModel.searchQuery.collectAsState()
    val filteredRecipes by viewModel.filteredRecipes.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val showRetry by viewModel.showRetry.collectAsState()

    val adaptedItems = filteredRecipes.map { buttonIdData ->
        RecipeButtonData(
            title = buttonIdData.title,
            icon = ImageVector.vectorResource(id = buttonIdData.icon),
            onClick = buttonIdData.onClick,
            modifier = buttonIdData.modifier
        )
    }

    LaunchedEffect(Unit) {
        viewModel.setOnRecipeClickListener { categoryId, categoryName ->
            onNavigateToCategory(categoryId, categoryName)
        }
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

        when {
            loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            showRetry -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(spacingExtraLarge)
                    ) {
                        Text(stringResource(id = R.string.failed_to_load_categories))
                        Button(onClick = { viewModel.retryApiCall() }) {
                            Text(stringResource(id = R.string.retry))
                        }
                    }
                }
            }
            else -> {
                CustomGrid(
                    items = adaptedItems,
                    modifier = modifier,
                    columns = 2,
                    verticalSpacing = spacingExtraLarge,
                    horizontalSpacing = spacingExtraLarge
                )
            }
        }
    }
}