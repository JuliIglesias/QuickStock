package com.example.quickStock.recipeScreens.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.quickStock.common.goBack.ScreenName
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun RecipeDetailScreen(
    recipe: RecipeData,
    modifier: Modifier = Modifier,
    onGoBack: () -> Unit
) {
    val scrollState = rememberScrollState()
    val primaryGreen = Color(0xFF4CAF50)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
    ) {
        // Header with back button and recipe name
        ScreenName(
            onGoBack = onGoBack,
            title = recipe.name,
        )

        // Content
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Recipe Image Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                recipe.image?.let { imageUrl ->
                    Image(
                        painter = rememberAsyncImagePainter(imageUrl),
                        contentDescription = recipe.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                } ?: Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.RestaurantMenu,
                        contentDescription = "Recipe",
                        tint = Color.White,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }

            // Ingredients Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Header
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.RestaurantMenu,
                            contentDescription = "Ingredients",
                            tint = primaryGreen,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Ingredients",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Divider()
                    Spacer(modifier = Modifier.height(12.dp))

                    // Ingredients list
                    recipe.ingredients.forEachIndexed { index, ingredient ->
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(primaryGreen.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "•",
                                    color = primaryGreen,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = ingredient,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }

            // Steps Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Header
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FormatListNumbered,
                            contentDescription = "Steps",
                            tint = primaryGreen,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Steps",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Divider()
                    Spacer(modifier = Modifier.height(12.dp))

                    // Steps list
                    recipe.steps.forEachIndexed { index, step ->
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(primaryGreen),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${index + 1}",
                                    color = Color.White,
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = step,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }

            // YouTube Video Card (if available)
            recipe.youtubeUrl?.let { url ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        // Header
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "YouTube Video",
                                tint = primaryGreen,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Video Tutorial",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Divider()
                        Spacer(modifier = Modifier.height(12.dp))

                        // YouTube WebView
                        val webViewState = rememberWebViewState(url = url)
                        WebView(
                            state = webViewState,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(240.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                    }
                }
            }
        }
    }
}