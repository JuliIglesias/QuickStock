package com.example.quickStock.screensUI.userConfig

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quickStock.R
import com.example.quickStock.ui.theme.*
import com.example.quickStock.viewModel.userConfig.UserSettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSettingsPage() {
    val viewModel = hiltViewModel<UserSettingsViewModel>()

    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    // Inicializar dark mode si es necesario (solo la primera vez)
    val systemDarkMode = isSystemInDarkTheme()
    LaunchedEffect(Unit) {
        viewModel.initDarkModeIfNeeded(!systemDarkMode)
    }

    // Use MaterialTheme colors directly
    val cardBackground = MaterialTheme.colorScheme.surfaceVariant
    val detailBackground = MaterialTheme.colorScheme.outlineVariant

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(paddingExtraLarge),
        verticalArrangement = Arrangement.spacedBy(spacingLarge)
    ) {
        // Header
        Text(
            text = stringResource(R.string.user_settings),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        // User Profile Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = elevationMedium),
            colors = CardDefaults.cardColors(
                containerColor = cardBackground
            ),
            shape = RoundedCornerShape(radiusMedium)
        ) {
            Column(
                modifier = Modifier.padding(paddingExtraLarge),
                verticalArrangement = Arrangement.spacedBy(spacingExtraLarge)
            ) {
                // Username Field
                OutlinedTextField(
                    value = uiState.username,
                    onValueChange = { viewModel.updateUsername(it) },
                    label = { Text(stringResource(R.string.username)) },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = stringResource(R.string.username),
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

                // Email Field
                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = { viewModel.updateEmail(it) },
                    label = { Text(stringResource(R.string.email)) },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = stringResource(R.string.email),
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
            }
        }

        // Preferences Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = elevationMedium),
            colors = CardDefaults.cardColors(
                containerColor = cardBackground
            ),
            shape = RoundedCornerShape(radiusMedium)
        ) {
            Column(
                modifier = Modifier.padding(paddingExtraLarge),
                verticalArrangement = Arrangement.spacedBy(spacingExtraLarge)
            ) {
                Text(
                    text = stringResource(R.string.preferences),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Language Selector
                ExposedDropdownMenuBox(
                    expanded = uiState.expandedLanguage,
                    onExpandedChange = { viewModel.toggleLanguageDropdown() }
                ) {
                    OutlinedTextField(
                        value = uiState.language,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(stringResource(R.string.language)) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Language,
                                contentDescription = stringResource(R.string.language),
                                tint = PrimaryGreen
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = uiState.expandedLanguage)
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryGreen,
                            focusedLabelColor = PrimaryGreen,
                            cursorColor = PrimaryGreen
                        )
                    )

                    ExposedDropdownMenu(
                        expanded = uiState.expandedLanguage,
                        onDismissRequest = { viewModel.dismissLanguageDropdown() }
                    ) {
                        viewModel.languages.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    viewModel.setLanguage(option)
                                    viewModel.dismissLanguageDropdown()
                                }
                            )
                        }
                    }
                }

                // Notification Toggle
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(radiusSmall))
                        .background(detailBackground)
                        .padding(horizontal = paddingExtraLarge, vertical = paddingLarge),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(paddingLarge)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = stringResource(R.string.notifications),
                            tint = PrimaryGreen
                        )
                        Text(
                            text = stringResource(R.string.enable_notifications),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Switch(
                        checked = uiState.notificationsEnabled,
                        onCheckedChange = { viewModel.toggleNotifications() },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = White,
                            checkedTrackColor = PrimaryGreen,
                            checkedBorderColor = PrimaryGreen,
                            uncheckedThumbColor = White,
                            uncheckedTrackColor = LightGray.copy(alpha = 0.5f),
                            uncheckedBorderColor = LightGray.copy(alpha = 0.5f)
                        )
                    )
                }

                // Dark Mode Toggle
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(radiusSmall))
                        .background(detailBackground)
                        .padding(horizontal = paddingExtraLarge, vertical = paddingLarge),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(paddingLarge)
                    ) {
                        Icon(
                            imageVector = Icons.Default.DarkMode,
                            contentDescription = stringResource(R.string.dark_mode),
                            tint = PrimaryGreen
                        )
                        Text(
                            text = stringResource(R.string.dark_mode),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Switch(
                        checked = uiState.darkModeEnabled,
                        onCheckedChange = { viewModel.toggleDarkMode() },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = White,
                            checkedTrackColor = PrimaryGreen,
                            checkedBorderColor = PrimaryGreen,
                            uncheckedThumbColor = White,
                            uncheckedTrackColor = LightGray.copy(alpha = 0.5f),
                            uncheckedBorderColor = LightGray.copy(alpha = 0.5f)
                        )
                    )
                }
            }
        }

        // Account Actions
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = elevationMedium),
            colors = CardDefaults.cardColors(
                containerColor = cardBackground
            ),
            shape = RoundedCornerShape(radiusMedium)
        ) {
            Column(
                modifier = Modifier.padding(paddingExtraLarge),
                verticalArrangement = Arrangement.spacedBy(spacingExtraLarge)
            ) {
                Text(
                    text = stringResource(R.string.account),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Save Settings Button
                Button(
                    onClick = { viewModel.saveSettings() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(heightButton),
                    shape = RoundedCornerShape(radiusRound),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
                ) {
                    Icon(
                        Icons.Default.Save,
                        contentDescription = stringResource(R.string.save_settings),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.width(spacingSmall))
                    Text(
                        stringResource(R.string.save_settings),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                // Logout Button
                Button(
                    onClick = { viewModel.logout() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(heightButton),
                    shape = RoundedCornerShape(radiusRound),
                    colors = ButtonDefaults.buttonColors(containerColor = ErrorRed)
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.Logout,
                        contentDescription = stringResource(R.string.logout),
                        tint = White
                    )
                    Spacer(modifier = Modifier.width(spacingSmall))
                    Text(
                        stringResource(R.string.logout),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                }
            }
        }
    }
}

@Composable
fun UserSettingsPagePreview(onClick: (String) -> Unit) {
    UserSettingsPage()
}

