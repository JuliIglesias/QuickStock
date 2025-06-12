package com.example.quickStock.screensUI.home

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
import androidx.compose.material3.MaterialTheme
import com.example.quickStock.R
import com.example.quickStock.model.home.CategoryButtonData
import com.example.quickStock.screensUI.common.SimpleSearchBar
import com.example.quickStock.screensUI.common.principal.CustomGrid
import com.example.quickStock.screensUI.icon.getCategoryIcon
import com.example.quickStock.ui.theme.paddingLarge
import com.example.quickStock.ui.theme.spacingExtraLarge
import com.example.quickStock.viewModel.home.GridCategoryViewModel

@Composable
fun ProductCategoryGrid(
    modifier: Modifier = Modifier,
    onCategoryClick: (String) -> Unit = {}
) {
    val viewModel = hiltViewModel<GridCategoryViewModel>()

    val searchQuery by viewModel.searchQuery.collectAsState()
    val categoriesWithProducts by viewModel.categoriesWithProducts.collectAsState()
    val categoriesWithoutProducts by viewModel.categoriesWithoutProducts.collectAsState()
    val selectedCategoryRoute by viewModel.selectedCategoryRoute.collectAsState()

    // Navegación
    selectedCategoryRoute?.let { route ->
        onCategoryClick(route)
        viewModel.resetCategorySelection()
    }

    // Helper para crear CategoryButtonData
    @Composable
    fun categoryButtonData(categoryName: String, hasProducts: Boolean): CategoryButtonData {
        val route = viewModel.getCategoryRoute(categoryName)
        return CategoryButtonData(
            title = categoryName,
            icon = getCategoryIcon(categoryName, ImageVector.vectorResource(R.drawable.ic_question_mark)),
            onClick = {
                if (route != null) {
                    viewModel.onCategorySelected(route)
                }
            },
            containerColor = if (!hasProducts) MaterialTheme.colorScheme.surfaceTint else null // El theme elige el color correcto
        )
    }

    val categoryItems =
        categoriesWithProducts.map { categoryButtonData(it, true) } +
        categoriesWithoutProducts.map { categoryButtonData(it, false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = paddingLarge),
        verticalArrangement = Arrangement.Top,
    ) {
        SimpleSearchBar(
            query = searchQuery,
            onQueryChange = { viewModel.updateSearchQuery(it) },
            onSearch = { /* Search is handled in updateSearchQuery */ },
            searchResults = categoriesWithProducts + categoriesWithoutProducts,
            modifier = Modifier
        )

        CustomGrid(
            items = categoryItems,
            modifier = modifier,
            columns = 2,
            verticalSpacing = spacingExtraLarge,
            horizontalSpacing = spacingExtraLarge
        )
    }
}

