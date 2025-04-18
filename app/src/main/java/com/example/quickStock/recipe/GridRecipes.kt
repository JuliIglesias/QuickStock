package com.example.quickStock.recipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.quickStock.R
import com.example.quickStock.common.CustomGrid
import com.example.quickStock.common.SimpleSearchBar

@Composable
fun GridRecipes(
    modifier: Modifier = Modifier,
    onRecipeClick: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val typeOfRecipes = context.resources.getStringArray(R.array.recipe_types)
    val typeOfRecipesIcons = mapOf(
        stringResource(R.string.fruits) to ImageVector.vectorResource(R.drawable.ic_fruit),
        stringResource(R.string.vegetables) to ImageVector.vectorResource(R.drawable.ic_veggie),
        stringResource(R.string.meat) to ImageVector.vectorResource(R.drawable.ic_meat),
        stringResource(R.string.seafood) to ImageVector.vectorResource(R.drawable.ic_fish_cat),
        stringResource(R.string.chicken) to ImageVector.vectorResource(R.drawable.ic_chicken),
        stringResource(R.string.salads) to ImageVector.vectorResource(R.drawable.ic_salad),
        stringResource(R.string.desserts) to ImageVector.vectorResource(R.drawable.ic_cookie),
        stringResource(R.string.alcoholic_beverages) to ImageVector.vectorResource(R.drawable.ic_cocktail),
        stringResource(R.string.pasta) to ImageVector.vectorResource(R.drawable.ic_pasta),
        stringResource(R.string.bakery) to ImageVector.vectorResource(R.drawable.ic_cake),
        stringResource(R.string.breakfast) to ImageVector.vectorResource(R.drawable.ic_egg),
        stringResource(R.string.fast_food) to ImageVector.vectorResource(R.drawable.ic_hamburger),
    )

    val buttonDataList = typeOfRecipes.map { recipeString ->
        RecipeButtonData(
            title = recipeString,
            icon = typeOfRecipesIcons[recipeString] ?: ImageVector.vectorResource(R.drawable.ic_question_mark),
            onClick = { onRecipeClick(recipeString) }
        )
    }

    val query = rememberSaveable { mutableStateOf("") }


    Column(modifier = Modifier.fillMaxWidth().fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        SimpleSearchBar(
            query = query.value,
            onQueryChange = { query.value = it },
            onSearch = { /* Handle search */ },
            searchResults = typeOfRecipes.toList(),
            modifier = Modifier
        )
        CustomGrid(
            items = buttonDataList,
            modifier = modifier,
            columns = 2,
            verticalSpacing = 16,
            horizontalSpacing = 16
        )
    }
}
