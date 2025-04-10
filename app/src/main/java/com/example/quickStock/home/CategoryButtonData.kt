package com.example.quickStock.home

import androidx.compose.ui.Modifier
import com.example.quickStock.common.ICardButton
import com.example.quickStock.icon.IconType

data class CategoryButtonData(
    override val title: String,
    override val icon: IconType,
    override val onClick: () -> Unit,
    override val modifier: Modifier = Modifier
) : ICardButton