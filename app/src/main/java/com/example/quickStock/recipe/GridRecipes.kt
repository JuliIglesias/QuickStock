package com.example.quickStock.recipe

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
        stringResource(R.string.fruits) to IconType.Drawable(painterResource(R.drawable.ic_fruit)),
        stringResource(R.string.vegetables) to IconType.Drawable(painterResource(R.drawable.ic_veggie)),
        stringResource(R.string.meat) to IconType.Drawable(painterResource(R.drawable.ic_meat)),
        stringResource(R.string.seafood) to IconType.Drawable(painterResource(R.drawable.ic_fish_cat)),
        stringResource(R.string.chicken) to IconType.Drawable(painterResource(R.drawable.ic_chicken)),
        stringResource(R.string.salads) to IconType.Drawable(painterResource(R.drawable.ic_salad)),
        stringResource(R.string.desserts) to IconType.Drawable(painterResource(R.drawable.ic_cookie)),
        stringResource(R.string.alcoholic_beverages) to IconType.Drawable(painterResource(R.drawable.ic_cocktail)),
        stringResource(R.string.pasta) to IconType.Drawable(painterResource(R.drawable.ic_pasta)),
        stringResource(R.string.bakery) to IconType.Drawable(painterResource(R.drawable.ic_cake)),
        stringResource(R.string.breakfast) to IconType.Drawable(painterResource(R.drawable.ic_egg)),
        stringResource(R.string.fast_food) to IconType.Drawable(painterResource(R.drawable.ic_hamburger)),
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
