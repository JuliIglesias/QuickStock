package com.example.quickStock.screensUI.home.stock

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickStock.R
import com.example.quickStock.screensUI.addProducts.Product
import com.example.quickStock.screensUI.common.goBack.ScreenName
import com.example.quickStock.screensUI.icon.getCategoryIcon
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
    val primaryGreen = Color(0xFF4CAF50)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
    ) {
        // Header with back button and product name
        ScreenName(
            onGoBack = onGoBack,
            title = product.name,
        )

        // Product info cards
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Brand and Category Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Brand row
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Store,
                            contentDescription = "Brand",
                            tint = primaryGreen,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Brand",
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
                    Spacer(modifier = Modifier.height(12.dp))

                    // Category row
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = if (getCategoryIcon(categoryName = product.category) == ImageVector.vectorResource(R.drawable.ic_question_mark)) {
                                Icons.Default.Category
                            } else {
                                getCategoryIcon(categoryName = product.category)
                            },
                            contentDescription = "Category",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Category",
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
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Header
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = "Expiration",
                            tint = primaryGreen,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Expiration Dates & Quantities",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(8.dp))

                    // Column headers
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Date",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "Quantity",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.width(80.dp),
                            maxLines = 1
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

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

                        val rowBackground = if (isExpiringSoon) {
                            Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFFFF9C4))
                                .padding(8.dp)
                        } else {
                            Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp, horizontal = 8.dp)
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
                                    .width(48.dp)
                                    .height(32.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(primaryGreen.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = item.quantity.toString(),
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Medium,
                                    color = primaryGreen
                                )
                            }
                        }

                        if (index < product.quantityExpirationDate.size - 1) {
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }

                    // Total row
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Total Stock",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.SemiBold
                        )

                        val totalQuantity = product.quantityExpirationDate.sumOf { it.quantity }
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(28.dp))
                                .background(primaryGreen),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = totalQuantity.toString(),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}