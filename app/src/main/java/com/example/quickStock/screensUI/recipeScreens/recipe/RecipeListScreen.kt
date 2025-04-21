package com.example.quickStock.screensUI.recipeScreens.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quickStock.R
import com.example.quickStock.screensUI.common.goBack.ScreenName
import com.example.quickStock.mocking.getRecipesByType
import com.example.quickStock.model.recipe.RecipeListData
import com.example.quickStock.ui.theme.*
import com.example.quickStock.viewModel.recipeScreens.GridRecipesViewModel
import com.example.quickStock.viewModel.recipeScreens.RecipeListViewModel

@Composable
fun RecipeListScreen(
    recipeType: String,
    modifier: Modifier = Modifier,
    onGoBack: () -> Unit,
    onClick: (String) -> Unit
) {
    val viewModel = hiltViewModel<RecipeListViewModel>()

    // Recolectar estados del ViewModel
    val screenTitle by viewModel.screenTitle.collectAsState()
    val recipes by viewModel.recipes.collectAsState()

    // Cargar recetas cuando se inicia la pantalla
    LaunchedEffect(recipeType) {
        viewModel.loadRecipesByType(recipeType) { recipeName ->
            onClick(recipeName) // Navegar al detalle de la receta
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingExtraLarge)
    ) {
        ScreenName(
            title = screenTitle,
            onGoBack = onGoBack,
        )

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
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = productButton.title,
                            fontSize = textSizeLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )

                        RecipeImage(
                            imageUrl = "https://www.themealdb.com/images/media/meals/wai9bw1619788844.jpg", //productButton.image,
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