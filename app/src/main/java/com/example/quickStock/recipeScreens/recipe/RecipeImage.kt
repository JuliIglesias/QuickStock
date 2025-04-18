package com.example.quickStock.recipeScreens.recipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.quickStock.R

@Composable
fun RecipeImage(imageUrl: String, contentDescription: String, modifier: Modifier) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxWidth(),
            placeholder = androidx.compose.ui.res.painterResource(R.drawable.placeholder), // Imagen de carga
            error = androidx.compose.ui.res.painterResource(R.drawable.error) // Imagen de error
        )
    }
}