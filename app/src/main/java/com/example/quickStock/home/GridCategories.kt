package com.example.quickStock.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Egg
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.LocalGroceryStore
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.SetMeal
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.quickStock.R
import com.example.quickStock.common.CardButton
import com.example.quickStock.common.CustomGrid

@Composable
fun ProductCategoryGrid(
    modifier: Modifier = Modifier,
    onCategoryClick: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val categories = context.resources.getStringArray(R.array.product_categories)
    val categoryIcons = mapOf(
        "Refrigerator" to Icons.Default.Kitchen,
        "Fruits" to Icons.Default.LocalFlorist,
        "Vegetables" to Icons.Default.LocalFlorist,
        "Meat" to Icons.Default.LocalDining,
        "Seafood" to Icons.Default.SetMeal,
        "Dairy & Eggs" to Icons.Default.Egg,
        "Bakery" to Icons.Default.Cake,
        "Grains & Pasta" to Icons.Default.LocalDining,
        "Canned Goods" to Icons.Default.LocalGroceryStore,
        "Condiments & Sauces" to Icons.Default.LocalGroceryStore,
        "Spices & Herbs" to Icons.Default.LocalGroceryStore,
        "Oils & Vinegars" to Icons.Default.LocalGroceryStore,
        "Snacks" to Icons.Default.LocalGroceryStore,
        "Beverages" to Icons.Default.LocalDrink,
        "Baking Supplies" to Icons.Default.LocalGroceryStore,
        "Frozen Fruits & Vegetables" to Icons.Default.LocalFlorist,
        "Frozen Meals" to Icons.Default.LocalDining,
        "Frozen Desserts" to Icons.Default.LocalDining,
        "Cleaning" to Icons.Default.CleaningServices,
        "Personal Care" to Icons.Default.Favorite,
        "Paper Products" to Icons.Default.Receipt,
        "Pet Supplies" to Icons.Default.Pets,
        "Electronics" to Icons.Default.Devices,
        "Baby" to Icons.Default.ChildCare,
        "Offers" to Icons.Default.LocalOffer
    )

    val buttonDataList = categories.map { categoryString ->
        CategoryButtonData(
            title = categoryString,
            icon = categoryIcons[categoryString] ?: Icons.Default.Favorite,
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
