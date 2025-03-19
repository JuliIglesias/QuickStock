package com.example.quickStock.Home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Egg
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.LocalGroceryStore
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.SetMeal
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.quickStock.R

@Composable
fun ProductCategoryGrid(
    modifier: Modifier = Modifier,
    onCategoryClick: (String) -> Unit = {} // Change the callback to use String
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

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement =Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(categories.toList()) { categoryString -> // Iterate over the list of strings
            CategoryButton(categoryString = categoryString,
                icon = categoryIcons[categoryString] ?: Icons.Default.Favorite,
                onClick = { onCategoryClick(categoryString) }) // Pass the string
        }
    }
}

@Composable
fun CategoryButton(
    categoryString: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.size(150.dp)
    ) {
        Column( modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            ){
            // Get the icon from the mapping, or use a default icon if not found
            Icon(
                imageVector = icon,
                contentDescription = categoryString,
                modifier = Modifier.size(48.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = categoryString,
                fontSize = 18.sp,
                textAlign = TextAlign.Center)
        }
    }
}