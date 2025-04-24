package com.example.quickStock.screensUI.home.stock

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.quickStock.R
import com.example.quickStock.model.addProduct.Product
import com.example.quickStock.screensUI.common.goBack.ScreenName
import com.example.quickStock.screensUI.common.secondary.ButtonIconAndName
import com.example.quickStock.screensUI.common.secondary.ReduceProductModal
import com.example.quickStock.screensUI.icon.getCategoryIcon
import com.example.quickStock.ui.theme.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProductDetailScreen(
    product: Product,
    modifier: Modifier = Modifier,
    onGoBack: () -> Unit
) {
    val scrollState = rememberScrollState()

    // Open ReduceProductModal when pressed on reduce button
    var showDialog by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(paddingZero, paddingExtraLarge),

        ) {
        // Header with back button and product name
        ScreenName(
            onGoBack = onGoBack,
            title = product.name,
        )

        // Product info cards
        Column(
            modifier = Modifier.padding(paddingExtraLarge)
        ) {
            // Brand and Category Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingExtraLarge),
                elevation = CardDefaults.cardElevation(defaultElevation = elevationMedium),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(paddingExtraLarge)
                ) {
                    // Brand row
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = paddingLarge)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Store,
                            contentDescription = stringResource(R.string.brand),
                            tint = PrimaryGreen,
                            modifier = Modifier.size(sizeIcon)
                        )
                        Spacer(modifier = Modifier.width(paddingLarge))
                        Column {
                            Text(
                                text = stringResource(R.string.brand),
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = product.brand,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(paddingLarge))

                    // Category row
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = getCategoryIcon(categoryName = product.category, Icons.Default.Category),
                            contentDescription = stringResource(R.string.category),
                            tint = Color.Unspecified,
                            modifier = Modifier.size(sizeIcon)
                        )
                        Spacer(modifier = Modifier.width(paddingLarge))
                        Column {
                            Text(
                                text = stringResource(R.string.category),
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = product.category,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            // Expiration Dates Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(elevationMedium),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(paddingExtraLarge)
                ) {
                    // Header
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = paddingExtraLarge)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = stringResource(R.string.expiration_dates_quantities),
                            tint = PrimaryGreen,
                            modifier = Modifier.size(sizeIcon)
                        )
                        Spacer(modifier = Modifier.width(paddingLarge))
                        Text(
                            text = stringResource(R.string.expiration_dates_quantities),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    HorizontalDivider(color = if (isSystemInDarkTheme()) DarkDividerColor else LightDividerColor)
                    Spacer(modifier = Modifier.height(spacingMedium))

                    // Column headers
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.date),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = stringResource(R.string.quantity),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.width(widthText),
                            maxLines = 1
                        )
                    }

                    Spacer(modifier = Modifier.height(spacingMedium))

                    // Sort expiration dates if possible
                    val sortedQuantityDates = try {
                        product.quantityExpirationDate.sortedBy {
                            LocalDate.parse(it.expiryDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        }
                    } catch (e: DateTimeParseException) {
                        product.quantityExpirationDate
                    }

                    // List items
                    sortedQuantityDates.forEachIndexed { index, item ->
                        val isExpiringSoon = try {
                            val expiryDate = LocalDate.parse(item.expiryDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                            val today = LocalDate.now()
                            expiryDate.isBefore(today.plusDays(7)) && expiryDate.isAfter(today.minusDays(1))
                        } catch (e: Exception) {
                            false
                        }

                        val expiringBadgeColor = if (isSystemInDarkTheme()) DarkExpiringBadge else LightExpiringBadge

                        val rowBackground = if (isExpiringSoon) {
                            Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(radiusMedium))
                                .background(expiringBadgeColor)
                                .padding(paddingMedium)
                        } else {
                            Modifier
                                .fillMaxWidth()
                                .padding(vertical = paddingSmall, horizontal = paddingMedium)
                        }

                        Row(
                            modifier = rowBackground,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Format date if possible
                            val formattedDate = try {
                                val date = LocalDate.parse(item.expiryDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                                date.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
                            } catch (e: Exception) {
                                item.expiryDate
                            }

                            Text(
                                text = formattedDate,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.weight(1f)
                            )

                            Box(
                                modifier = Modifier
                                    .width(sizeQuantityBadge)
                                    .height(heightRow)
                                    .clip(RoundedCornerShape(radiusLarge))
                                    .background(PrimaryGreen.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = item.quantity.toString(),
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Medium,
                                    color = PrimaryGreen
                                )
                            }
                        }

                        if (index < product.quantityExpirationDate.size - 1) {
                            Spacer(modifier = Modifier.height(spacingSmall))
                        }
                    }

                    // Total row
                    Spacer(modifier = Modifier.height(paddingExtraLarge))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(paddingLarge))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.total_stock),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.SemiBold
                        )

                        val totalQuantity = product.quantityExpirationDate.sumOf { it.quantity }
                        val quantityCircleColor = if (isSystemInDarkTheme()) DarkQuantityBadgeBackground else LightQuantityBadgeBackground
                        val quantityTextColor = if (isSystemInDarkTheme()) DarkQuantityTextColor else LightQuantityTextColor

                        Box(
                            modifier = Modifier
                                .size(sizeQuantityCircle)
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(radiusRound))
                                .background(quantityCircleColor),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = totalQuantity.toString(),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = quantityTextColor
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(paddingZero, paddingExtraLarge),
                verticalArrangement = Arrangement.spacedBy(spacingExtraLarge)
            ) {
                // reduce Button
                ButtonIconAndName(
                    onClick = {
                        showDialog = true
                    },
                    text = stringResource(id = R.string.reduce_product),
                    icon = Icons.Default.Remove
                )

                // Text input dialog
                if (showDialog) {
                    ReduceProductModal(
                        productName = product.name,
                        productQuantity = 50,
                        onDismiss = {
                            showDialog = false
                        },
                        onConfirm = { quantity ->
                            // Handle the confirm action
                            // You can call a function to reduce the product quantity here
                            // For example, update the product in the database or state
                            showDialog = false
                        }
                    )
                }
            }
        }
    }
}