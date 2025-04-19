package com.example.quickStock.recipeScreens.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickStock.common.goBack.ScreenName
import com.example.quickStock.mocking.getRecipesByType

@Composable
fun RecipeListScreen(
    recipeType: String,
    modifier: Modifier = Modifier,
    onGoBack: () -> Unit,
    onClick: (String) -> Unit
) {
    val recipeTypeList = getRecipesByType(recipeType)

    val recipeButtons = recipeTypeList.map { recipe ->
        RecipeButtonData(
            title = recipe.name,
            image = recipe.image,
            onClick = { onClick(recipe.id) }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ScreenName(
            title = "Recipes of $recipeType",
            onGoBack = onGoBack,
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(recipeButtons) { productButton ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                        .clickable { productButton.onClick() }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = productButton.title,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )

                        RecipeImage(
                            imageUrl = "https://www.themealdb.com/images/media/meals/wai9bw1619788844.jpg", //productButton.image,
                            contentDescription = productButton.title,
                            modifier = Modifier
                                .size(96.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )

                    }
                }
            }
        }
    }
}