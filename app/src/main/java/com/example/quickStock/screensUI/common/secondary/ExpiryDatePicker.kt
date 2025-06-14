package com.example.quickStock.screensUI.common.secondary

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.quickStock.R
import com.example.quickStock.ui.theme.ErrorRed
import com.example.quickStock.ui.theme.PrimaryGreen
import com.example.quickStock.ui.theme.SuccessGreen
import com.example.quickStock.ui.theme.paddingExtraLarge
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.quickStock.screensUI.common.principal.ModernTextButton
import com.example.quickStock.ui.theme.DarkGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpiryDatePicker(
    selectedDate: String,
    onDateSelected: (String) -> Unit
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    var showDatePicker by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // Create a Calendar instance to get today's date for the initial selected date
    val calendar = Calendar.getInstance()

    // Detect press and show date picker
    LaunchedEffect(isPressed) {
        if (isPressed) {
            showDatePicker = true
        }
    }

    // Initialize the date picker state with today's date if no date is selected
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = if (selectedDate.isNotEmpty()) {
            try {
                SimpleDateFormat(context.getString(
                    R.string.yyyy_mm_dd
                ), Locale.getDefault()).parse(selectedDate)?.time
            } catch (e: Exception) {
                calendar.timeInMillis
            }
        } else {
            calendar.timeInMillis
        }
    )

    // Show date picker dialog when needed
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                ModernTextButton(
                    text = stringResource(id = R.string.select_date),
                    textColor = SuccessGreen,
                    onClick = {
                        datePickerState.selectedDateMillis?.let { timestamp ->
                            // Convertir a LocalDate en UTC y luego a zona local para evitar desfase
                            val localDate = java.time.Instant.ofEpochMilli(timestamp)
                                .atZone(java.time.ZoneOffset.UTC)
                                .toLocalDate()
                            val formattedDate = localDate.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                            onDateSelected(formattedDate)
                        }
                        showDatePicker = false
                    }
                )
            },
            dismissButton = {
                ModernTextButton(
                    text = stringResource(id = R.string.cancel),
                    textColor = ErrorRed,
                    onClick = { showDatePicker = false }
                )
            }
        ) {
            DatePicker(
                state = datePickerState,
                title = null,
                modifier = Modifier.padding(paddingExtraLarge),
            )
        }
    }

    OutlinedTextField(
        value = selectedDate,
        onValueChange = {},
        label = { Text(stringResource(id = R.string.product_expiration_date)) },
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.CalendarToday,
                contentDescription = stringResource(id = R.string.product_expiration_date),
                tint = PrimaryGreen
            )
        },
        readOnly = true,
        enabled = true,
        interactionSource = interactionSource,
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryGreen,
            focusedLabelColor = PrimaryGreen,
            cursorColor = PrimaryGreen
        ),
        textStyle = TextStyle(textAlign = TextAlign.Start),
        placeholder = {
            if (selectedDate.isEmpty()) {
                Text(
                    text = stringResource(id =R.string.tap_select_date),
                    style = TextStyle(
                        color = DarkGray
                    )
                )
            }
        }
    )
}

