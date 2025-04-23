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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quickStock.R
import com.example.quickStock.model.addProduct.Product
import com.example.quickStock.screensUI.common.secondary.ButtonIconAndName
import com.example.quickStock.screensUI.icon.getCategoryIcon
import com.example.quickStock.ui.theme.LightGray
import com.example.quickStock.ui.theme.PrimaryGreen
import com.example.quickStock.ui.theme.elevationMedium
import com.example.quickStock.ui.theme.elevationSmall
import com.example.quickStock.ui.theme.heightButton
import com.example.quickStock.ui.theme.heightRow
import com.example.quickStock.ui.theme.noElevation
import com.example.quickStock.ui.theme.paddingExtraLarge
import com.example.quickStock.ui.theme.radiusExtraSmall
import com.example.quickStock.ui.theme.radiusLarge
import com.example.quickStock.ui.theme.radiusRound
import com.example.quickStock.ui.theme.radiusSmall
import com.example.quickStock.ui.theme.sizeCircleButton
import com.example.quickStock.ui.theme.sizeCircles
import com.example.quickStock.ui.theme.sizeIcon
import com.example.quickStock.ui.theme.spacingExtraLarge
import com.example.quickStock.ui.theme.spacingMedium
import com.example.quickStock.ui.theme.widthBoxSurvey
import com.example.quickStock.ui.theme.widthText
import com.example.quickStock.viewModel.addProducts.ProductSurveyViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductSurvey(onProductAdded: (Product) -> Unit) {
    val viewModel = hiltViewModel<ProductSurveyViewModel>()

    val uiState by viewModel.uiState.collectAsState()
    val isDropdownExpanded by viewModel.isDropdownExpanded.collectAsState()
    val context = LocalContext.current
    val categories = context.resources.getStringArray(R.array.product_categories)
    val scrollState = rememberScrollState()

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
            .padding(paddingExtraLarge)
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(spacingExtraLarge)
    ) {
        // Header
        Text(
            text = stringResource(id = R.string.add_product),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        // Forms Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = elevationMedium),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(paddingExtraLarge),
                verticalArrangement = Arrangement.spacedBy(spacingExtraLarge)
            ) {
                // Product ID Field
                OutlinedTextField(
                    value = uiState.productId,
                    onValueChange = { viewModel.updateProductId(it) },
                    label = { Text("Product ID") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.QrCodeScanner,
                            contentDescription = "Product ID",
                            tint = PrimaryGreen
                        )
                    },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryGreen,
                        focusedLabelColor = PrimaryGreen,
                        cursorColor = PrimaryGreen
                    )
                )

                // Product Name Field
                OutlinedTextField(
                    value = uiState.productName,
                    onValueChange = { viewModel.updateProductName(it) },
                    label = { Text("Product Name") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.ShoppingBag,
                            contentDescription = "Product Name",
                            tint = PrimaryGreen
                        )
                    },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryGreen,
                        focusedLabelColor = PrimaryGreen,
                        cursorColor = PrimaryGreen
                    )
                )

                // Brand Field
                OutlinedTextField(
                    value = uiState.productBrand,
                    onValueChange = { viewModel.updateProductBrand(it) },
                    label = { Text("Brand") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Store,
                            contentDescription = "Brand",
                            tint = PrimaryGreen
                        )
                    },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryGreen,
                        focusedLabelColor = PrimaryGreen,
                        cursorColor = PrimaryGreen
                    )
                )

                // Expiry Date Field
                OutlinedTextField(
                    value = uiState.productExpiryDate,
                    onValueChange = { viewModel.updateExpiryDate(it) },
                    label = { Text("Expiry Date (YYYY-MM-DD)") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = "Expiry Date",
                            tint = PrimaryGreen
                        )
                    },
                    placeholder = { Text("2024-04-30") },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryGreen,
                        focusedLabelColor = PrimaryGreen,
                        cursorColor = PrimaryGreen
                    )
                )

                // Category Dropdown
                ExposedDropdownMenuBox(
                    expanded = isDropdownExpanded,
                    onExpandedChange = { viewModel.toggleDropdown() }
                ) {
                    OutlinedTextField(
                        value = uiState.productCategory,
                        onValueChange = {
                            viewModel.updateProductCategory(it)
                        },
                        label = { Text("Category") },
                        leadingIcon = {
                            Icon(
                                imageVector = getCategoryIcon(categoryName = uiState.productCategory, Icons.Default.Category),
                                contentDescription = "Category",
                                modifier = Modifier.size(sizeIcon),
                                tint = if (uiState.productCategory.isEmpty() || isDropdownExpanded) PrimaryGreen else { Color.Unspecified }
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownExpanded)
                        },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryGreen,
                            focusedLabelColor = PrimaryGreen,
                            cursorColor = PrimaryGreen
                        )
                    )

                    ExposedDropdownMenu(
                        expanded = isDropdownExpanded,
                        onDismissRequest = { viewModel.closeDropdown() },
                        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
                    ) {
                        categories.filter {
                            it.contains(uiState.productCategory, ignoreCase = true) || uiState.productCategory.isEmpty()
                        }.forEach { category ->
                            DropdownMenuItem(
                                text = { Text(category) },
                                onClick = {
                                    viewModel.updateProductCategory(category)
                                    viewModel.closeDropdown()
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = categoryIcons[category] ?: Icons.Default.Category,
                                        contentDescription = category,
                                        modifier = Modifier.size(sizeIcon),
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
                    elevation = CardDefaults.cardElevation(defaultElevation = noElevation),
                    colors = CardDefaults.cardColors(
                        containerColor = PrimaryGreen.copy(alpha = 0.1f)
                    ),
                    shape = RoundedCornerShape(radiusSmall)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(paddingExtraLarge)
                    ) {
                        Text(
                            text = "Quantity",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Medium
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(spacingMedium)
                        ) {
                            IconButton(
                                onClick = { viewModel.decrementQuantity() },
                                modifier = Modifier
                                    .size(sizeCircleButton)
                                    .clip(RoundedCornerShape(radiusLarge))
                                    .background(
                                        if (uiState.productQuantity > 1) MaterialTheme.colorScheme.primary else LightGray.copy(
                                            alpha = 0.5f
                                        )
                                    )
                            ) {
                                Icon(
                                    Icons.Default.Remove,
                                    contentDescription = "Decrease Quantity",
                                    tint = MaterialTheme.colorScheme.onSecondary
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .width(widthBoxSurvey)
                                    .height(heightRow)
                                    .clip(RoundedCornerShape(radiusExtraSmall)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = uiState.productQuantity.toString(),
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            IconButton(
                                onClick = { viewModel.incrementQuantity() },
                                modifier = Modifier
                                    .size(sizeCircleButton)
                                    .clip(RoundedCornerShape(radiusLarge))
                                    .background(MaterialTheme.colorScheme.primary)
                            ) {
                                Icon(
                                    Icons.Default.Add,
                                    contentDescription = "Increase Quantity",
                                    tint = MaterialTheme.colorScheme.onSecondary
                                )
                            }
                        }
                    }
                }
            }
        }

        // Add Button
        ButtonIconAndName(
            onClick = {
                viewModel.addProduct { newProduct ->
                    onProductAdded(newProduct)
                }
            },
            text = stringResource(id = R.string.add_product),
            icon = Icons.Default.Add
        )
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