package com.example.quickStock.addProducts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun ProductSurvey(onProductAdded: (Product) -> Unit) {
    var productId by remember { mutableStateOf("") }
    var productName by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }
    var productBrand by remember { mutableStateOf("") }
    var productCategory by remember { mutableStateOf("") }
    var productQuantity by remember { mutableIntStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Add Product",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = productId,
            onValueChange = { productId = it },
            label = { Text("Product ID") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = productName,
            onValueChange = { productName = it },
            label = { Text("Product Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = productPrice,
            onValueChange = { productPrice = it },
            label = { Text("Price") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = productBrand,
            onValueChange = { productBrand = it },
            label = { Text("Brand") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = productCategory,
            onValueChange = { productCategory = it },
            label = { Text("Category") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Quantity:")
            IconButton(onClick = { if (productQuantity > 1) productQuantity-- }) {
                Icon(
                    Icons.Default.Remove,
                    contentDescription = "Decrease Quantity"
                )
            }
            Text(productQuantity.toString())
            IconButton(onClick = { productQuantity++ }) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Increase Quantity"
                )
            }
        }

        Button(
            onClick = {
                val price = productPrice.toDoubleOrNull()
                if (price != null) {
                    onProductAdded(
                        Product(
                            id = productId,
                            name = productName,
                            price = price,
                            brand = productBrand,
                            category = productCategory,
                            quantity = productQuantity
                        )
                    )
                    // Reset fields after adding
                    productId = ""
                    productName = ""
                    productPrice = ""
                    productBrand = ""
                    productCategory = ""
                    productQuantity = 1
                } else {
                    // Handle invalid price (e.g., show error message)
                    println("Invalid price format.")
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add Product")
        }
    }
}