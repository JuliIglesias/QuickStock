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
import androidx.compose.ui.platform.LocalContext
import com.example.quickstore.R

@Composable
fun ProductCategoryGrid(
    modifier: Modifier = Modifier,
    onCategoryClick: (String) -> Unit = {} // Change the callback to use String
) {
    val context = LocalContext.current
    val categories = context.resources.getStringArray(R.array.product_categories)

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
                onClick = { onCategoryClick(categoryString) }) // Pass the string
        }
    }
}

@Composable
fun CategoryButton(
    categoryString: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.size(150.dp)
    ) {
        // Get the icon from the mapping, or use a default icon if not found
        val icon = Icons.Default.Favorite
        Icon(
            imageVector = icon,
            contentDescription = categoryString,
        )
        Text(text = categoryString)
    }
}