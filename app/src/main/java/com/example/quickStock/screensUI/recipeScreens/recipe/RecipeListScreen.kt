package com.example.quickStock.screensUI.recipeScreens.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.quickStock.screensUI.common.goBack.ScreenName
import com.example.quickStock.ui.theme.*
import com.example.quickStock.viewModel.recipeScreens.RecipeListViewModel

@Composable
fun RecipeListScreen(
    categoryId: Int,
    recipeType: String,
    modifier: Modifier = Modifier,
    onGoBack: () -> Unit,
    onClick: (String) -> Unit
) {
    val viewModel = hiltViewModel<RecipeListViewModel>()

    // Recolectar estados del ViewModel
    val recipes by viewModel.recipes.collectAsStateWithLifecycle()
    val screenTitle by viewModel.screenTitle.collectAsStateWithLifecycle()
    val loading by viewModel.loading.collectAsStateWithLifecycle()
    val showRetry by viewModel.showRetry.collectAsStateWithLifecycle()


    // Cargar recetas cuando se inicia la pantalla
    LaunchedEffect(recipeType) {
        viewModel.loadRecipesByCategory(recipeType, onClick)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        if (loading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (showRetry) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(stringResource(id = com.example.quickStock.R.string.failed_to_load_recipes))
                    Spacer(modifier = Modifier.height(spacingMedium))
                    Button(onClick = { viewModel.retryApiCall(recipeType, onClick) }) {
                        Text(stringResource(id = com.example.quickStock.R.string.retry))
                    }
                }
            }
        } else {
            ScreenName(
                title = screenTitle,
                onGoBack = onGoBack,
            )

            Column(
                modifier = Modifier.padding(paddingExtraLarge)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(spacingMedium)
                ) {
                    items(recipes) { productButton ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(radiusSmall)
                                )
                                .clickable { productButton.onClick() }
                                .padding(paddingExtraLarge)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = productButton.title,
                                    fontSize = textSizeLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(end = spacingSmall)
                                )

                                RecipeImage(
                                    imageUrl = productButton.image,
                                    contentDescription = productButton.title,
                                    modifier = Modifier
                                        .size(sizeImageMedium)
                                        .clip(RoundedCornerShape(radiusSmall))
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}