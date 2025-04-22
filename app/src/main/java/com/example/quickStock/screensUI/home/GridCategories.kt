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
    val filteredCategoryNames by viewModel.filteredCategories.collectAsState()
    val selectedCategoryRoute by viewModel.selectedCategoryRoute.collectAsState()

    // Navigate to selected category if one is selected
    selectedCategoryRoute?.let { route ->
        onCategoryClick(route)
        viewModel.resetCategorySelection()
    }

    // Convert category names to CategoryButtonData objects with icons
    val categoryItems = filteredCategoryNames.map { categoryName ->
        val route = viewModel.getCategoryRoute(categoryName)

        CategoryButtonData(
            title = categoryName,
            icon = getCategoryIcon(categoryName, ImageVector.vectorResource(R.drawable.ic_question_mark)),
            onClick = {
                if (route != null) {
                    viewModel.onCategorySelected(route)
                }
            }
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
            onQueryChange = { viewModel.updateSearchQuery(it) },
            onSearch = { /* Search is handled in updateSearchQuery */ },
            searchResults = filteredCategoryNames,
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