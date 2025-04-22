package com.example.quickStock.model.recipe

import androidx.compose.ui.Modifier
import com.example.quickStock.model.common.ICardButton
import com.example.quickStock.model.common.ICardIdButton

data class RecipeButtonIdData(
    override val title: String,
    override val icon: Int,
    override val onClick: () -> Unit,
    override val modifier: Modifier = Modifier
) : ICardIdButton