package com.example.quickStock.screensUI.recipeScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.quickStock.R
import com.example.quickStock.screensUI.common.principal.CustomGrid
import com.example.quickStock.screensUI.common.SimpleSearchBar
import com.example.quickStock.screensUI.icon.getRecipesTypesIcon
import com.example.quickStock.screensUI.navigation.categories.CategoryRoutes
import com.example.quickStock.screensUI.navigation.categories.RecipeRoutes

@Composable
fun GridRecipes(
    modifier: Modifier = Modifier,
    onRecipeClick: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val typeOfRecipes = context.resources.getStringArray(R.array.recipe_types)

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

    val query = rememberSaveable { mutableStateOf("") }
    val filteredRecipes = rememberSaveable { mutableStateOf(buttonDataList) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        SimpleSearchBar(
            query = query.value,
            onQueryChange = { newQuery ->
                query.value = newQuery
                filteredRecipes.value = buttonDataList.filter { it.title.contains(newQuery, ignoreCase = true) }
            },
            onSearch = {
                filteredRecipes.value = buttonDataList.filter { it.title.contains(query.value, ignoreCase = true) }
           },
            searchResults = filteredRecipes.value.map { it.title },
            modifier = Modifier
        )
        CustomGrid(
            items = filteredRecipes.value,
            modifier = modifier,
            columns = 2,
            verticalSpacing = 16,
            horizontalSpacing = 16
        )
    }
}