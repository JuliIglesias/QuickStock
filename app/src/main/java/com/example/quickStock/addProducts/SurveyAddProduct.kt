package com.example.quickStock.addProducts

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductSurvey(onProductAdded: (Product) -> Unit) {
    var productId by remember { mutableStateOf("") }
    var productName by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }
    var productBrand by remember { mutableStateOf("") }
    var productCategory by remember { mutableStateOf("") }
    var productQuantity by remember { mutableIntStateOf(1) }
    var productExpiryDate by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val categories = remember { mutableStateOf(listOf("Refrigerator", "Fruits", "Vegetables", "Meat", "Seafood")) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Add Product",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        item {
            OutlinedTextField(
                value = productId,
                onValueChange = { productId = it },
                label = { Text("Product ID") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            OutlinedTextField(
                value = productName,
                onValueChange = { productName = it },
                label = { Text("Product Name") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            OutlinedTextField(
                value = productPrice,
                onValueChange = { productPrice = it },
                label = { Text("Price") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        item {
            OutlinedTextField(
                value = productBrand,
                onValueChange = { productBrand = it },
                label = { Text("Brand") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            OutlinedTextField(
                value = productExpiryDate,
                onValueChange = { productExpiryDate = it },
                label = { Text("Expiry Date") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = productCategory,
                    onValueChange = {
                        productCategory = it
                        expanded = true
                    },
                    label = { Text("Category") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    }
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    categories.value.filter { it.contains(productCategory, ignoreCase = true) }.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category) },
                            onClick = {
                                productCategory = category
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        item {
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
        }

        item {
            Button(
                onClick = {
                    val price = productPrice.toDoubleOrNull()
                    if (price != null) {
                        onProductAdded(
                            Product(
                                id = productId,
                                name = productName,
                                brand = productBrand,
                                category = productCategory,
                                quantity = productQuantity,
                                expiryDate = productExpiryDate
                            )
                        )
                        // Reset fields after adding
                        productId = ""
                        productName = ""
                        productBrand = ""
                        productCategory = ""
                        productQuantity = 1
                        productExpiryDate = ""
                    } else {
                        // Handle invalid price (e.g., show error message)
                        println("Invalid price format.")
                    }
                }
            ) {
                Text("Add Product")
            }
        }
    }
}


@Composable
fun AddProductSurvey(onClick: (String) -> Unit) {
//    BarcodeScannerScreen { capturedImageUri ->
//        // Handle the captured image URI here
//        if (capturedImageUri != null) {
//            Log.d("BarcodeScanner", "Image captured: $capturedImageUri")
//            // You can now pass this URI to a barcode scanning library
//            //or display the image to the user.
//        } else {
//            Log.e("BarcodeScanner", "Failed to capture image.")
//        }
//    }

    ProductSurvey(onProductAdded = {
        Log.d("ProductSurvey", "Product added: $it")
    })
}