package com.example.quickStock.screensUI.common.secondary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quickStock.R
import com.example.quickStock.model.addProduct.QuantityExpirationDate
import com.example.quickStock.screensUI.common.principal.ModernTextButton
import com.example.quickStock.ui.theme.ErrorRed
import com.example.quickStock.ui.theme.LightGray
import com.example.quickStock.ui.theme.PrimaryGreen
import com.example.quickStock.ui.theme.SuccessGreen
import com.example.quickStock.ui.theme.heightRow
import com.example.quickStock.ui.theme.paddingExtraLarge
import com.example.quickStock.ui.theme.paddingMedium
import com.example.quickStock.ui.theme.paddingSmall
import com.example.quickStock.ui.theme.radiusExtraSmall
import com.example.quickStock.ui.theme.radiusLarge
import com.example.quickStock.ui.theme.sizeCircleButton
import com.example.quickStock.ui.theme.spacingExtraLarge
import com.example.quickStock.ui.theme.spacingLarge
import com.example.quickStock.ui.theme.spacingMedium
import com.example.quickStock.ui.theme.spacingSmall
import com.example.quickStock.ui.theme.widthBoxSurvey
import com.example.quickStock.viewModel.common.secondary.ReduceProductViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ReduceProductModal(
    productName: String,
    expiryDates: List<QuantityExpirationDate>,
    onDismiss: () -> Unit,
    onConfirm: (expiryDate: String, quantity: Int) -> Unit
) {
    val viewModel = hiltViewModel<ReduceProductViewModel>()

    val context = LocalContext.current
    val selectedDateIndex by viewModel.selectedDateIndex.collectAsState()
    val quantityToReduce by viewModel.quantityToReduce.collectAsState()

    val selectedItem = expiryDates.getOrNull(selectedDateIndex)
    val maxQuantity = selectedItem?.quantity ?: 0

    LaunchedEffect(Unit) {
        viewModel.resetState()
    }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {
            Column(
                modifier = Modifier
                    .padding(paddingExtraLarge)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(paddingMedium)
            ) {
                Text(
                    text = stringResource(id = R.string.reduce_quantity_of_product, productName),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(spacingMedium))

                // Select expiry date
                Text(
                    text = stringResource(R.string.select_expiry_date),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(spacingSmall))

                // Dropdown for expiry dates
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = paddingMedium)
                ) {
                    var expanded by remember { mutableStateOf(false) }

                    OutlinedTextField(
                        value = selectedItem?.expiryDate ?: context.getString(
                            R.string.nothing_String
                        ),
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(stringResource(R.string.product_expiration_date)) },
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            IconButton(onClick = { expanded = true }) {
                                Icon(
                                    imageVector = Icons.Default.CalendarToday,
                                    contentDescription = stringResource(R.string.select_expiry_date),
                                    tint = PrimaryGreen
                                )
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryGreen,
                            focusedLabelColor = PrimaryGreen
                        )
                    )

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.fillMaxWidth(0.9f)
                    ) {
                        expiryDates.forEachIndexed { index, item ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = stringResource(R.string.quantity_units, item.expiryDate, item.quantity),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                },
                                onClick = {
                                    viewModel.updateSelectedDateIndex(index, item.quantity)
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(spacingSmall))

                // Quantity selector
                Text(
                    text = stringResource(R.string.quantity_to_reduce),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(spacingSmall))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Decrease button
                    IconButton(
                        onClick = { viewModel.decrementQuantity()},
                        modifier = Modifier
                            .size(sizeCircleButton)
                            .clip(RoundedCornerShape(radiusLarge))
                            .background(
                                if (quantityToReduce > 1) MaterialTheme.colorScheme.primary else LightGray.copy(
                                    alpha = 0.5f
                                )
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = stringResource(R.string.decrease_quantity),
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                    }

                    // Quantity display
                    Box(
                        modifier = Modifier
                            .width(widthBoxSurvey)
                            .height(heightRow)
                            .clip(RoundedCornerShape(radiusExtraSmall)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = quantityToReduce.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Increase button
                    IconButton(
                        onClick = { viewModel.incrementQuantity(maxQuantity)  },
                        modifier = Modifier
                            .size(sizeCircleButton)
                            .clip(RoundedCornerShape(radiusLarge))
                            .background(
                                if (quantityToReduce < maxQuantity) MaterialTheme.colorScheme.primary else LightGray.copy(
                                    alpha = 0.5f
                                )
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(id = R.string.increase_quantity),
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }

                // Maximum available
                Text(
                    text = stringResource(
                        id = R.string.max_available,
                        maxQuantity
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.align(Alignment.End)
                )

                Spacer(modifier = Modifier.height(spacingExtraLarge))

                // Buttons row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Cancel button with circle effect
                    ModernTextButton(
                        text = stringResource(id = R.string.cancel),
                        textColor = ErrorRed,
                        onClick = { onDismiss() },
                    )

                    ModernTextButton(
                        text = stringResource(id = R.string.confirm),
                        textColor = SuccessGreen,
                        onClick = {
                            selectedItem?.let {
                                onConfirm(it.expiryDate, quantityToReduce)
                            }
                            onDismiss()
                        }
                    )
                }
            }
        }
    }
}