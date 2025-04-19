package com.example.quickStock.home.stock

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickStock.addProducts.Product
import com.example.quickStock.common.goBack.ScreenName
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun ProductDetailScreen(
    product: Product,
    modifier: Modifier = Modifier,
    onGoBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        ScreenName(onGoBack = onGoBack,
            title = product.name,
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Brand
        Text(
            text = "Brand: ${product.brand}",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Category
        Text(
            text = "Category: ${product.category}",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Expiration Dates and Quantities
        Text(
            text = "Expirations Dates and Quantities:",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        product.quantityExpirationDate.forEach { quantityExpiration ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Expiration Date (Left Column)
                Text(
                    text = quantityExpiration.expiryDate,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp
                )

                // Quantity (Right Column)
                Text(
                    text = quantityExpiration.quantity.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }



    }
}