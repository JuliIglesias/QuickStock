package com.example.quickStock.recipeScreens.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickStock.R
import com.example.quickStock.common.goBack.ScreenName

@Composable
fun RecipeDetailScreen(
    recipe: RecipeData,
    modifier: Modifier = Modifier,
    onGoBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        ScreenName(onGoBack = onGoBack,
            title = recipe.name,
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Imagen de la receta
//        RecipeImage(
//            imageUrl = recipe.image,
//            contentDescription = recipe.name,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp) // Altura del rectángulo
//        )

        Spacer(modifier = Modifier.height(16.dp))

        // Ingredientes
        Text(
            text = "Ingredientes:",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 20.sp
        )
        recipe.ingredients.forEach { ingredient ->
            Text(
                text = "- $ingredient",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Pasos
        Text(
            text = "Pasos:",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 20.sp
        )
        recipe.steps.forEachIndexed { index, step ->
            Text(
                text = "${index + 1}. $step",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp
            )
        }
    }
}