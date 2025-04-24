package com.example.quickStock.screensUI.common.secondary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog
import com.example.quickStock.ui.theme.ErrorRed
import com.example.quickStock.ui.theme.paddingExtraLarge
import com.example.quickStock.ui.theme.paddingMedium
import com.example.quickStock.ui.theme.spacingLarge

@Composable
fun ReduceProductModal(
    productName: String,
    productQuantity: Int,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    // Implementation of the modal dialog to reduce product quantity
    // This would typically include a TextField for input and buttons for confirm and cancel
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier.padding(paddingExtraLarge),
                verticalArrangement = Arrangement.spacedBy(paddingMedium)
            ) {
                Text(
                    text = "Reduce Quantity of $productName",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = onDismiss
                    ) {
                        Text("Cancel")
                    }

                    Spacer(modifier = Modifier.width(spacingLarge))

                    Button(
                        onClick = {
                            // Handle the confirm action
                            onConfirm(productQuantity)
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ErrorRed,
                        )
                    ) {
                        Text("Create")
                    }
                }
            }
        }
    }
}