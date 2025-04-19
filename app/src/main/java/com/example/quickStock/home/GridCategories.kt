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

    // Mapeo de iconos - lo mantenemos en la UI ya que necesitamos Context y recursos
    val categoryIcons = mapOf(
        stringResource(R.string.refrigerator) to ImageVector.vectorResource(R.drawable.ic_refrigerator),
        stringResource(R.string.fruits) to ImageVector.vectorResource(R.drawable.ic_fruit),
        stringResource(R.string.vegetables) to ImageVector.vectorResource(R.drawable.ic_veggie),
        stringResource(R.string.meat) to ImageVector.vectorResource(R.drawable.ic_meat),
        stringResource(R.string.seafood) to ImageVector.vectorResource(R.drawable.ic_fish_cat),
        stringResource(R.string.dairy_and_eggs) to ImageVector.vectorResource(R.drawable.ic_egg),
        stringResource(R.string.bakery) to ImageVector.vectorResource(R.drawable.ic_cake),
        stringResource(R.string.grains_and_pasta) to ImageVector.vectorResource(R.drawable.ic_pasta),
        stringResource(R.string.canned_goods) to ImageVector.vectorResource(R.drawable.ic_canned_sauce),
        stringResource(R.string.spices_and_herbs) to ImageVector.vectorResource(R.drawable.ic_herbs),
        stringResource(R.string.snacks) to ImageVector.vectorResource(R.drawable.ic_cookie),
        stringResource(R.string.beverages) to ImageVector.vectorResource(R.drawable.ic_cocktail),
        stringResource(R.string.bathroom) to ImageVector.vectorResource(R.drawable.ic_bath_paper),
        stringResource(R.string.personal_care) to ImageVector.vectorResource(R.drawable.ic_personal_care),
        stringResource(R.string.paper_products) to ImageVector.vectorResource(R.drawable.ic_school),
        stringResource(R.string.pet_supplies) to ImageVector.vectorResource(R.drawable.ic_paw_pet),
        stringResource(R.string.electronics) to ImageVector.vectorResource(R.drawable.ic_computer),
        stringResource(R.string.baby) to ImageVector.vectorResource(R.drawable.ic_baby_bottle),
        stringResource(R.string.household) to ImageVector.vectorResource(R.drawable.ic_kitchen_suplies),
    )

    val defaultIcon = ImageVector.vectorResource(R.drawable.ic_question_mark)

    // Convertimos los nombres de categorías en objetos CategoryButtonData con íconos
    val categoryItems = filteredCategoryNames.map { categoryName ->
        val route = viewModel.getCategoryRoute(categoryName)

        CategoryButtonData(
            title = categoryName,
            icon = categoryIcons[categoryName] ?: defaultIcon,
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



