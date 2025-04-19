package com.example.quickStock.recipeScreens.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.quickStock.R
import com.example.quickStock.common.goBack.ScreenName
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

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
        RecipeImage(
            imageUrl = recipe.image, // "https://i.kym-cdn.com/photos/images/newsfeed/002/422/058/391.jpg",
            contentDescription = recipe.name,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Ingredients
        Text(
            text = "Ingredients:",
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

        // Steps
        Text(
            text = "Steps:",
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

        // Yutu
        recipe.youtubeUrl?.let { url ->
            Text(
                text = "Youtube video:",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            val webViewState = rememberWebViewState(url = "https://www.youtube.com/watch?v=G1CAsRvbxmg")//url)
            WebView(
                state = webViewState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
    }
}