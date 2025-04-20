package com.example.quickStock.screensUI.addProducts

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.quickStock.R
import com.example.quickStock.model.addProduct.Product
import com.example.quickStock.model.addProduct.QuantityExpirationDate
import com.example.quickStock.screensUI.icon.getCategoryIcon


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductSurvey(onProductAdded: (Product) -> Unit) {
    var productId by remember { mutableStateOf("") }
    var productName by remember { mutableStateOf("") }
    var productBrand by remember { mutableStateOf("") }
    var productCategory by remember { mutableStateOf("") }
    var productQuantity by remember { mutableIntStateOf(1) }
    var productExpiryDate by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val categories = context.resources.getStringArray(R.array.product_categories)
    val scrollState = rememberScrollState()
    val primaryGreen = Color(0xFF4CAF50)

    val categoryIcons = mapOf(
        "Refrigerator" to ImageVector.vectorResource(R.drawable.ic_refrigerator),
        "Fruits" to ImageVector.vectorResource(R.drawable.ic_fruit),
        "Vegetables" to ImageVector.vectorResource(R.drawable.ic_veggie),
        "Meat" to ImageVector.vectorResource(R.drawable.ic_meat),
        "Seafood" to ImageVector.vectorResource(R.drawable.ic_fish_cat),
        "Dairy & Eggs" to ImageVector.vectorResource(R.drawable.ic_egg),
        "Bakery" to ImageVector.vectorResource(R.drawable.ic_cake),
        "Grains & Pasta" to ImageVector.vectorResource(R.drawable.ic_pasta),
        "Canned Goods" to ImageVector.vectorResource(R.drawable.ic_canned_sauce),
        "Spices & Herbs" to ImageVector.vectorResource(R.drawable.ic_herbs),
        "Snacks" to ImageVector.vectorResource(R.drawable.ic_cookie),
        "Beverages" to ImageVector.vectorResource(R.drawable.ic_cocktail),
        "Bathroom" to ImageVector.vectorResource(R.drawable.ic_bath_paper),
        "Personal Care" to ImageVector.vectorResource(R.drawable.ic_personal_care),
        "Paper Products" to ImageVector.vectorResource(R.drawable.ic_school),
        "Pet Supplies" to ImageVector.vectorResource(R.drawable.ic_paw_pet),
        "Electronics" to ImageVector.vectorResource(R.drawable.ic_computer),
        "Baby" to ImageVector.vectorResource(R.drawable.ic_baby_bottle),
        "Household" to ImageVector.vectorResource(R.drawable.ic_kitchen_suplies)
    )



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(16.dp)
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Text(
            text = "Add Product",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        // Forms Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Product ID Field
                OutlinedTextField(
                    value = productId,
                    onValueChange = { productId = it },
                    label = { Text("Product ID") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.QrCodeScanner,
                            contentDescription = "Product ID",
                            tint = primaryGreen
                        )
                    },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = primaryGreen,
                        focusedLabelColor = primaryGreen,
                        cursorColor = primaryGreen
                    )
                )

                // Product Name Field
                OutlinedTextField(
                    value = productName,
                    onValueChange = { productName = it },
                    label = { Text("Product Name") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.ShoppingBag,
                            contentDescription = "Product Name",
                            tint = primaryGreen
                        )
                    },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = primaryGreen,
                        focusedLabelColor = primaryGreen,
                        cursorColor = primaryGreen
                    )
                )

                // Brand Field
                OutlinedTextField(
                    value = productBrand,
                    onValueChange = { productBrand = it },
                    label = { Text("Brand") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Store,
                            contentDescription = "Brand",
                            tint = primaryGreen
                        )
                    },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = primaryGreen,
                        focusedLabelColor = primaryGreen,
                        cursorColor = primaryGreen
                    )
                )

                // Expiry Date Field
                OutlinedTextField(
                    value = productExpiryDate,
                    onValueChange = { productExpiryDate = it },
                    label = { Text("Expiry Date (YYYY-MM-DD)") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = "Expiry Date",
                            tint = primaryGreen
                        )
                    },
                    placeholder = { Text("2024-04-30") },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = primaryGreen,
                        focusedLabelColor = primaryGreen,
                        cursorColor = primaryGreen
                    )
                )

                // Category Dropdown
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
                        leadingIcon = {
                            Icon(
                                imageVector = getCategoryIcon(categoryName = productCategory, Icons.Default.Category),
                                contentDescription = "Category",
                                modifier = Modifier.size(24.dp),
                                tint = if (productCategory.isEmpty() || expanded) primaryGreen else { Color.Unspecified }
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = primaryGreen,
                            focusedLabelColor = primaryGreen,
                            cursorColor = primaryGreen
                        )
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
                    ) {
                        categories.filter {
                            it.contains(productCategory, ignoreCase = true) || productCategory.isEmpty()
                        }.forEach { category ->
                            DropdownMenuItem(
                                text = { Text(category) },
                                onClick = {
                                    productCategory = category
                                    expanded = false
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = categoryIcons[category] ?: Icons.Default.Category,
                                        contentDescription = category,
                                        modifier = Modifier.size(24.dp),
                                        tint = Color.Unspecified
                                    )
                                }
                            )
                        }
                    }
                }

                // Quantity Selector
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = primaryGreen.copy(alpha = 0.1f)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Quantity",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Medium
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            IconButton(
                                onClick = { if (productQuantity > 1) productQuantity-- },
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(RoundedCornerShape(18.dp))
                                    .background(
                                        if (productQuantity > 1) primaryGreen else Color.Gray.copy(
                                            alpha = 0.5f
                                        )
                                    )
                            ) {
                                Icon(
                                    Icons.Default.Remove,
                                    contentDescription = "Decrease Quantity",
                                    tint = Color.White
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .width(48.dp)
                                    .height(36.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(MaterialTheme.colorScheme.surface),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = productQuantity.toString(),
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            IconButton(
                                onClick = { productQuantity++ },
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(RoundedCornerShape(18.dp))
                                    .background(primaryGreen)
                            ) {
                                Icon(
                                    Icons.Default.Add,
                                    contentDescription = "Increase Quantity",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }

        // Add Button
        Button(
            onClick = {
                onProductAdded(
                    Product(
                        id = productId,
                        name = productName,
                        brand = productBrand,
                        category = productCategory,
                        quantityExpirationDate = listOf(
                            QuantityExpirationDate(
                                quantity = productQuantity,
                                expiryDate = productExpiryDate
                            )
                        )
                    )
                )
                // Reset fields after adding
                productId = ""
                productName = ""
                productBrand = ""
                productCategory = ""
                productQuantity = 1
                productExpiryDate = ""
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(containerColor = primaryGreen)
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Add Product",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "Add Product",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
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