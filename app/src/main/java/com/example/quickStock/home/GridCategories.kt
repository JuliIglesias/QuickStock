package com.example.quickStock.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.quickStock.R
import com.example.quickStock.common.CustomGrid
import com.example.quickStock.common.SimpleSearchBar

@Composable
fun ProductCategoryGrid(
    modifier: Modifier = Modifier,
    onCategoryClick: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val categories = context.resources.getStringArray(R.array.product_categories)
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
    val buttonDataList = categories.map { categoryString ->
        CategoryButtonData(
            title = categoryString,
            icon = categoryIcons[categoryString] ?: ImageVector.vectorResource(R.drawable.ic_question_mark),
            onClick = { onCategoryClick(categoryString) }
        )
    }

    Column(modifier = Modifier.fillMaxWidth().fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        SimpleSearchBar(
            textFieldState = TextFieldState(),
            onSearch = { /* Handle search */ },
            searchResults = categories.toList(),
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
