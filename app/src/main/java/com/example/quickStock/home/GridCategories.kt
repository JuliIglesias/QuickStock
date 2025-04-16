package com.example.quickStock.home

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.quickStock.R
import com.example.quickStock.common.CustomGrid
import com.example.quickStock.icon.IconType

@Composable
fun ProductCategoryGrid(
    modifier: Modifier = Modifier,
    onCategoryClick: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val categories = context.resources.getStringArray(R.array.product_categories)
    val categoryIcons = mapOf(
        "Refrigerator" to IconType.Drawable(painterResource(R.drawable.ic_refrigerator)),
        "Fruits" to IconType.Drawable(painterResource(R.drawable.ic_fruit)),
        "Vegetables" to IconType.Drawable(painterResource(R.drawable.ic_veggie)),
        "Meat" to IconType.Drawable(painterResource(R.drawable.ic_meat)),
        "Seafood" to IconType.Drawable(painterResource(R.drawable.ic_fish_cat)),
        "Dairy & Eggs" to IconType.Drawable(painterResource(R.drawable.ic_egg)),
        "Bakery" to IconType.Drawable(painterResource(R.drawable.ic_cake)),
        "Grains & Pasta" to IconType.Drawable(painterResource(R.drawable.ic_pasta)),
        "Canned Goods" to IconType.Drawable(painterResource(R.drawable.ic_canned_sauce)),
        "Spices & Herbs" to IconType.Drawable(painterResource(R.drawable.ic_herbs)),
        "Snacks" to IconType.Drawable(painterResource(R.drawable.ic_cookie)),
        "Beverages" to IconType.Drawable(painterResource(R.drawable.ic_cocktail)),
        "Bathroom" to IconType.Drawable(painterResource(R.drawable.ic_bath_paper)),
        "Personal Care" to IconType.Drawable(painterResource(R.drawable.ic_personal_care)),
        "Paper Products" to IconType.Drawable(painterResource(R.drawable.ic_school)),
        "Pet Supplies" to IconType.Drawable(painterResource(R.drawable.ic_paw_pet)),
        "Electronics" to IconType.Drawable(painterResource(R.drawable.ic_computer)),
        "Baby" to IconType.Drawable(painterResource(R.drawable.ic_baby_bottle)),
        "Household" to IconType.Drawable(painterResource(R.drawable.ic_kitchen_suplies)),
    )

    val buttonDataList = categories.map { categoryString ->
        CategoryButtonData(
            title = categoryString,
            icon = categoryIcons[categoryString] ?: IconType.Drawable(painterResource(R.drawable.ic_question_mark)),
            onClick = { onCategoryClick(categoryString) }
        )
    }

    CustomGrid(
        items = buttonDataList,
        modifier = modifier,
        columns = 2,
        verticalSpacing = 16,
        horizontalSpacing = 16
    )
}
