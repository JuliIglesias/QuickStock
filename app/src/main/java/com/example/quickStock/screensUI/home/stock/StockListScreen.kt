package com.example.quickStock.screensUI.home.stock

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quickStock.screensUI.common.goBack.ScreenName
import com.example.quickStock.mocking.getProductsByCategory
import com.example.quickStock.screensUI.navigation.categories.CategoryRoutes
import com.example.quickStock.viewModel.home.stock.StockListViewModel

@Composable
fun StockListScreen(
    category: String,
    modifier: Modifier = Modifier,
    onGoBack: () -> Unit,
    onClick: (String) -> Unit
) {
    val viewModel = hiltViewModel<StockListViewModel>()
    val products by viewModel.products.collectAsState()

    // Carga los productos cuando se inicia el composable o cambia la categoría
    LaunchedEffect(category) {
        viewModel.loadProductsByCategory(category, onClick)
    }


    val categoryRoute = CategoryRoutes.entries.find { it.name == category }
    val formattedCategoryName = categoryRoute?.getFormattedName() ?: category

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ScreenName(
            title = "Stock for $formattedCategoryName",
            onGoBack = onGoBack,
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(products) { productButton ->
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
                        Text(
                            text = productButton.quantity.toString(),
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}