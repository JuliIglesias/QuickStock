package com.example.quickStock.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quickStock.R
import com.example.quickStock.common.principal.CustomGrid
import com.example.quickStock.common.SimpleSearchBar
import com.example.quickStock.icon.getCategoryIcon

@Composable
fun ProductCategoryGrid(
    modifier: Modifier = Modifier,
    onCategoryClick: (String) -> Unit = {}
) {
    val viewModel = hiltViewModel<GridCategoryViewModel>()

    val searchQuery by viewModel.searchQuery.collectAsState()
    val filteredCategoryNames by viewModel.filteredCategories.collectAsState()
    val selectedCategoryRoute by viewModel.selectedCategoryRoute.collectAsState()

    // Si se seleccionó una categoría, navega a ella
    selectedCategoryRoute?.let { route ->
        onCategoryClick(route)
        viewModel.resetCategorySelection() // Reinicia el estado después de navegar
    }

    // Convertimos los nombres de categorías en objetos CategoryButtonData con íconos
    val categoryItems = filteredCategoryNames.map { categoryName ->
        val route = viewModel.getCategoryRoute(categoryName)

        CategoryButtonData(
            title = categoryName,
            icon = getCategoryIcon(categoryName),
            onClick = {
                if (route != null) {
                    viewModel.onCategorySelected(route)
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        SimpleSearchBar(
            query = searchQuery,
            onQueryChange = { viewModel.updateSearchQuery(it) },
            onSearch = { /* La búsqueda ya se maneja en updateSearchQuery */ },
            searchResults = filteredCategoryNames,
            modifier = Modifier
        )

        CustomGrid(
            items = categoryItems,
            modifier = modifier,
            columns = 2,
            verticalSpacing = 16,
            horizontalSpacing = 16
        )
    }
}



