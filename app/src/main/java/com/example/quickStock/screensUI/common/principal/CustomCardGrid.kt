package com.example.quickStock.screensUI.common.principal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.quickStock.model.common.ICardButton
import com.example.quickStock.ui.theme.Transparent
import com.example.quickStock.ui.theme.spacingExtraLarge

@Composable
fun CustomGrid(
    items: List<ICardButton>,
    modifier: Modifier = Modifier,
    columns: Int = 2,
    verticalSpacing: androidx.compose.ui.unit.Dp = spacingExtraLarge,
    horizontalSpacing: androidx.compose.ui.unit.Dp = spacingExtraLarge,
    onItemClick: (ICardButton) -> Unit = {}
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .background(color = Transparent),
        columns = GridCells.Fixed(columns),
        verticalArrangement = Arrangement.spacedBy(verticalSpacing),
        horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
    ) {
        items(items) { item ->
            CardButton(buttonData = item)
        }
    }
}