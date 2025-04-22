package com.example.quickStock.screensUI.icon

import android.content.Context
import com.example.quickStock.R

fun getRecipesTypesIconId(context: Context, recipeTypeName: String): Int {
    val typeOfRecipesIcons = mapOf(
        context.getString(R.string.beef) to R.drawable.ic_meat,
        context.getString(R.string.chicken) to R.drawable.ic_chicken,
        context.getString(R.string.dessert) to R.drawable.ic_cake,
        context.getString(R.string.lamb) to R.drawable.ic_lamb, //
        context.getString(R.string.miscellaneous) to R.drawable.ic_beans,
        context.getString(R.string.pasta) to R.drawable.ic_pasta,
        context.getString(R.string.pork) to R.drawable.ic_pork,
        context.getString(R.string.seafood) to R.drawable.ic_fish_cat,
        context.getString(R.string.side) to R.drawable.ic_salad,
        context.getString(R.string.starter) to R.drawable.ic_bread,
        context.getString(R.string.vegan) to R.drawable.ic_veggie,
        context.getString(R.string.vegetarian) to R.drawable.ic_vegetarian,
        context.getString(R.string.breakfast) to R.drawable.ic_egg,
        context.getString(R.string.goat) to R.drawable.ic_goat,
        context.getString(R.string.alcoholic_beverages) to R.drawable.ic_cocktail,
    )
    return typeOfRecipesIcons[recipeTypeName] ?: R.drawable.ic_question_mark
}