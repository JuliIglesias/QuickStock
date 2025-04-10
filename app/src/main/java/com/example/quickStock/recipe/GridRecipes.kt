package com.example.quickStock.recipe

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.LocalGroceryStore
import androidx.compose.material.icons.filled.SetMeal
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.quickStock.R
import com.example.quickStock.common.CustomGrid
import com.example.quickStock.icon.IconType

@Composable
fun GridRecipes(
    modifier: Modifier = Modifier,
    onRecipeClick: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val typeOfRecipes = context.resources.getStringArray(R.array.recipe_types)
    val typeOfRecipesIcons = mapOf(
        "Fruits" to IconType.Drawable(painterResource(R.drawable.ic_fruit)),
        "Vegetables" to IconType.Drawable(painterResource(R.drawable.ic_veggie)),
        "Meat" to IconType.Drawable(painterResource(R.drawable.ic_meat)),
        "Seafood" to IconType.Drawable(painterResource(R.drawable.ic_fish_cat)),
        "Chicken" to IconType.Drawable(painterResource(R.drawable.ic_chicken)),
        "Vegetarian" to IconType.Drawable(painterResource(R.drawable.ic_salad)),
        "Desserts" to IconType.Drawable(painterResource(R.drawable.ic_cookie)),
        "Alcoholic Beverages" to IconType.Drawable(painterResource(R.drawable.ic_cocktail)),
        "Pasta" to IconType.Drawable(painterResource(R.drawable.ic_pasta)),
        "Bakery" to IconType.Drawable(painterResource(R.drawable.ic_cake)),
        "Breakfast" to IconType.Drawable(painterResource(R.drawable.ic_egg)),
        "Fast Food" to IconType.Drawable(painterResource(R.drawable.ic_hamburger)),
    )

    val buttonDataList = typeOfRecipes.map { recipeString ->
        RecipeButtonData(
            title = recipeString,
            icon = typeOfRecipesIcons[recipeString] ?: IconType.Drawable(painterResource(R.drawable.ic_question_mark)),
            onClick = { onRecipeClick(recipeString) }
        )
    }

    CustomGrid(
        items = buttonDataList,
        modifier = modifier,
        columns = 2,
        verticalSpacing = 16,
        horizontalSpacing = 16
    )
}
