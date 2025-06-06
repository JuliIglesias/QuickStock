package com.example.quickStock.screensUI.icon

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.quickStock.R

@Composable
fun getCategoryIcon(categoryName: String, doNotHaveIcon: ImageVector): ImageVector {
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

    return categoryIcons[categoryName] ?: doNotHaveIcon
}
