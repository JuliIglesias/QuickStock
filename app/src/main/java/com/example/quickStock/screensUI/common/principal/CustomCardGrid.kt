package com.example.quickStock.screensUI.common.principal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomGrid(
    items: List<ICardButton>,
    modifier: Modifier = Modifier,
    columns: Int = 2,
    verticalSpacing: Int = 16,
    horizontalSpacing: Int = 16,
    onItemClick: (ICardButton) -> Unit = {}
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize().background(color = androidx.compose.ui.graphics.Color.Transparent),
        columns = GridCells.Fixed(columns),
        verticalArrangement = Arrangement.spacedBy(verticalSpacing.dp),
        horizontalArrangement = Arrangement.spacedBy(horizontalSpacing.dp),

    ) {
        items(items) { item ->
            CardButton(buttonData = item)
        }
    }
}