package com.example.quickStock.preview

// Preview para ProductCategoryGrid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Liquor
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.quickStock.R
import com.example.quickStock.model.home.CategoryButtonData
import com.example.quickStock.screensUI.common.SimpleSearchBar
import com.example.quickStock.screensUI.common.principal.CustomGrid
import com.example.quickStock.screensUI.home.ProductCategoryGrid
import com.example.quickStock.ui.theme.QuickStockTheme
import com.example.quickStock.ui.theme.paddingExtraLarge
import com.example.quickStock.ui.theme.paddingLarge
import com.example.quickStock.ui.theme.spacingExtraLarge

@Preview(showBackground = true)
@Composable
fun ProductCategoryGridPreview() {
    QuickStockTheme {
        ProductCategoryGridPreviewContent()
    }
}

@Composable
fun ProductCategoryGridPreviewContent() {
    // Lista estática de categorías para el preview
    val categoryItems = listOf(
        CategoryButtonData(
            title = stringResource(R.string.comidas),
            icon = Icons.Default.Restaurant,
            onClick = {}
        ),
        CategoryButtonData(
            title = stringResource(R.string.bebidas),
            icon = Icons.Default.Liquor,
            onClick = {}
        ),
        CategoryButtonData(
            title = stringResource(R.string.snacks),
            icon = Icons.Default.Fastfood,
            onClick = {}
        ),
        CategoryButtonData(
            title = stringResource(R.string.productos),
            icon = Icons.Default.ShoppingCart,
            onClick = {}
        )
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingExtraLarge),
                verticalArrangement = Arrangement.Top,
            ) {
                SimpleSearchBar(
                    query = stringResource(
                        R.string.nothing_String
                    ),
                    onQueryChange = {},
                    onSearch = { },
                    searchResults = listOf(stringResource(
                        R.string.comidas
                    ), stringResource(
                        R.string.bebidas
                    ), stringResource(
                        R.string.snacks
                    ), stringResource(
                        R.string.productos
                    )),
                    modifier = Modifier
                )

                CustomGrid(
                    items = categoryItems,
                    columns = 2,
                    verticalSpacing = spacingExtraLarge,
                    horizontalSpacing = spacingExtraLarge
                )
            }
        }
    }

}
