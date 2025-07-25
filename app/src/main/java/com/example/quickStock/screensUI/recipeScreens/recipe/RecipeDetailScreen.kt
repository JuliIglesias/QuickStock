package com.example.quickStock.screensUI.recipeScreens.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.quickStock.R
import com.example.quickStock.model.recipe.RecipeData
import com.example.quickStock.screensUI.common.goBack.ScreenName
import com.example.quickStock.screensUI.common.principal.CollapsibleCard
import com.example.quickStock.ui.theme.*
import com.example.quickStock.viewModel.recipeScreens.RecipeDetailViewModel
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun RecipeDetailScreen(
    recipeId: String?,
    onGoBack: () -> Unit
) {
    val viewModel = hiltViewModel<RecipeDetailViewModel>()

    val loading by viewModel.loading.collectAsStateWithLifecycle()
    val showRetry by viewModel.showRetry.collectAsStateWithLifecycle()
    val recipe by viewModel.recipe.collectAsState()

    LaunchedEffect(recipeId) {
        viewModel.loadRecipeDetails(recipeId.toString())
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        when {
            loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            showRetry -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.failed_to_load_recipe),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(spacingMedium))
                    Button(
                        onClick = { viewModel.retryLoadingRecipe() },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = stringResource(id = R.string.retry)
                        )
                        Spacer(modifier = Modifier.width(spacingSmall))
                        Text(text = stringResource(id = R.string.retry))
                    }
                }
            }
            recipe != null -> {
                RecipeDetailContent(
                    recipe = recipe!!,
                    onGoBack = onGoBack
                )
            }
        }
    }
}

@Composable
private fun RecipeDetailContent(
    recipe: RecipeData,
    onGoBack: () -> Unit
) {
    val scrollState = rememberScrollState()
    val context = androidx.compose.ui.platform.LocalContext.current

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
            modifier = Modifier.padding(paddingExtraLarge)
        ) {
            // Recipe Image Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingExtraLarge),
                elevation = CardDefaults.cardElevation(defaultElevation = elevationMedium),
                shape = RoundedCornerShape(radiusMedium)
            ) {
                recipe.image?.let { imageUrl ->
                    Image(
                        painter = rememberAsyncImagePainter(imageUrl),
                        contentDescription = recipe.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(sizeImageLarge)
                    )
                } ?: Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(heightCard)
                        .background(LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.RestaurantMenu,
                        contentDescription = stringResource(R.string.recipe),
                        tint = White,
                        modifier = Modifier.size(sizeLargeIcon)
                    )
                }
            }

            // Ingredients with Measurements Card
            CollapsibleCard(
                title = stringResource(R.string.ingredients),
                icon = Icons.Default.Kitchen,
                iconTint = PrimaryGreen,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingExtraLarge),
                initiallyExpanded = true,
                content = {

                    // Ingredients and measurements list
                    recipe.ingredients.forEachIndexed { index, ingredient ->
                        val measurement = if (index < recipe.measurements.size) recipe.measurements[index] else context.getString(
                            R.string.nothing_String
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = paddingMedium),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Ingredient dot and name
                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(sizeCircles)
                                        .clip(RoundedCornerShape(radiusSmall))
                                        .background(PrimaryGreen.copy(alpha = 0.15f)),
                                    contentAlignment = Alignment.TopCenter
                                ) {
                                    Text(
                                        text = stringResource(R.string.dot),
                                        color = PrimaryGreen,
                                        fontWeight = FontWeight.Bold,
                                    )
                                }
                                Spacer(modifier = Modifier.width(spacingMedium))
                                Text(
                                    text = ingredient,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            // Measurement
                            Text(
                                text = measurement,
                                modifier = Modifier.weight(1f),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                                textAlign = TextAlign.End
                            )
                        }

                        if (index < recipe.ingredients.size - 1) {
                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = paddingSmall),
                                color = LightGray.copy(alpha = 0.5f)
                            )
                        }
                    }
                }
            )

            // Steps Card
            CollapsibleCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingExtraLarge),
                title = stringResource(R.string.steps),
                icon = Icons.Default.FormatListNumbered,
                iconTint = PrimaryGreen,
                initiallyExpanded = true,
            ) {
                // Steps list
                recipe.steps.forEachIndexed { index, step ->
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.padding(vertical = paddingMedium)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(sizeIcon)
                                .clip(RoundedCornerShape(radiusMedium))
                                .background(PrimaryGreen),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.numeration, index + 1),
                                color = White,
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.width(paddingLarge))
                        Text(
                            text = step,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }


            // YouTube Video Card (if available)
            if (!recipe.youtubeUrl.isNullOrEmpty()) {
                CollapsibleCard(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.video_tutorial),
                    icon = Icons.Default.PlayArrow,
                    iconTint = PrimaryGreen,
                    initiallyExpanded = true,
                ) {
                    // YouTube WebView
                    val embedUrl = recipe.youtubeUrl.replace("watch?v=", "embed/")
                    val webViewState = rememberWebViewState(url = embedUrl)
                    WebView(
                        state = webViewState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(heightVideo)
                            .clip(RoundedCornerShape(radiusSmall)),
                        onCreated = { webView ->
                            webView.settings.javaScriptEnabled = true
                        }
                    )
                }
            }
        }
    }
}