package com.example.quickstore.Home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.LocalGroceryStore
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.quickstore.R


@Composable
fun ProductCategoryGrid(
    categories: List<Int>,
    modifier: Modifier = Modifier,
    onCategoryClick: (Int) -> Unit = {} // Add a callback for category clicks
) {
    Text(text = stringResource(id = R.string.app_name))
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(categories) { categoryStringId -> // Change the type to Int
            CategoryButton(categoryStringId = categoryStringId, onClick = { onCategoryClick(categoryStringId) }) // Pass the string ID
        }
    }
}

@Composable
fun CategoryButton(
    categoryStringId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.size(150.dp)
    ) {
        Text(text = stringResource(id = categoryStringId))
        // Get the icon from the mapping, or use a default icon if not found
        val icon = categoryIcons[categoryStringId] ?: Icons.Default.Favorite
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = categoryStringId),
        )
    }
}



// Icon Mapping
val categoryIcons: Map<Int, ImageVector> = mapOf(
    R.string.refrigerator to Icons.Default.LocalGroceryStore,
    R.string.fruits to Icons.Default.LocalFlorist,
    R.string.vegetables to Icons.Default.LocalFlorist,
    R.string.meat to Icons.Default.LocalDining,
    R.string.seafood to Icons.Default.LocalDining,
    R.string.dairy_eggs to Icons.Default.LocalOffer,
    R.string.bakery to Icons.Default.LocalOffer,
    R.string.grains_pasta to Icons.Default.LocalOffer,
    R.string.canned_goods to Icons.Default.LocalOffer,
    R.string.condiments_sauces to Icons.Default.LocalOffer,
    R.string.spices_herbs to Icons.Default.LocalOffer,
    R.string.oils_vinegars to Icons.Default.LocalOffer,
    R.string.snacks to Icons.Default.LocalOffer,
    R.string.beverages to Icons.Default.LocalDrink,
    R.string.baking_supplies to Icons.Default.LocalOffer,
    R.string.frozen_fruits_vegetables to Icons.Default.LocalFlorist,
    R.string.frozen_meals to Icons.Default.LocalDining,
    R.string.frozen_desserts to Icons.Default.LocalOffer,
    R.string.cleaning to Icons.Default.CleaningServices,
    R.string.personal_care to Icons.Default.ShoppingCart,
    R.string.paper_products to Icons.Default.LocalOffer,
    R.string.pet_supplies to Icons.Default.Pets,
    R.string.electronics to Icons.Default.ShoppingCart,
    R.string.baby to Icons.Default.ShoppingCart
)
