package com.example.quickStock.home.stock

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickStock.common.goBack.ScreenName
import com.example.quickStock.mocking.getProductsByCategory

@Composable
fun StockListScreen(
    category: String,
    modifier: Modifier = Modifier,
    onGoBack: () -> Unit,
    onClick: (String) -> Unit
) {
    val products = getProductsByCategory(category)

    val productButtons = products.map { product ->
        StockButtonData(
            title = product.name,
            quantity = product.quantityExpirationDate.sumOf { it.quantity },
            onClick = { onClick(product.id) }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ScreenName(
            title = "Stock for $category",
            onGoBack = onGoBack,
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(productButtons) { productButton ->
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