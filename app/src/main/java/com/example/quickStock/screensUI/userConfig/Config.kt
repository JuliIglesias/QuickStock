package com.example.quickStock.screensUI.userConfig

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quickStock.R
import com.example.quickStock.ui.theme.*
import com.example.quickStock.viewModel.userConfig.UserSettingsViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.isGranted

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun UserSettingsPage() {
    val viewModel = hiltViewModel<UserSettingsViewModel>()
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    // Estados locales para los campos, inicializados desde DataStore
    var username by remember { mutableStateOf(context.getString(
        R.string.nothing_String
    )) }
    var email by remember { mutableStateOf(context.getString(
        R.string.nothing_String
    )) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var darkModeEnabled by remember { mutableStateOf(false) }
    var language by remember { mutableStateOf(context.getString(
        R.string.english
    )) }
    var expandedLanguage by remember { mutableStateOf(false) }

    // Solicitar permiso de notificaciones solo cuando el usuario activa el toggle
    val postNotificationPermission = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

    val systemDarkMode = !isSystemInDarkTheme()

    // Cargar datos desde DataStore al entrar a la pantalla
    LaunchedEffect(Unit) {
        username = viewModel.getUsername()
        email = viewModel.getEmail()
        notificationsEnabled = viewModel.getNotificationsEnabled()
        darkModeEnabled = viewModel.getDarkModeEnabled()
        language = viewModel.getLanguage()
        expandedLanguage = viewModel.getExpandedLanguage()
        viewModel.initDarkModeIfNeeded(systemDarkMode)
        viewModel.updateUserFromAuthManager()
        // Refrescar por si updateUserFromAuthManager cambia algo
        username = viewModel.getUsername()
        email = viewModel.getEmail()
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
                    value = username,
                    onValueChange = {
                        username = it
                        viewModel.updateUsername(it)
                    },
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
                    value = email,
                    onValueChange = {
                        email = it
                        viewModel.updateEmail(it)
                    },
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
                    expanded = expandedLanguage,
                    onExpandedChange = {
                        expandedLanguage = !expandedLanguage
                        viewModel.toggleLanguageDropdown()
                    }
                ) {
                    OutlinedTextField(
                        value = language,
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
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedLanguage)
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryGreen,
                            focusedLabelColor = PrimaryGreen,
                            cursorColor = PrimaryGreen
                        )
                    )

                    ExposedDropdownMenu(
                        expanded = expandedLanguage,
                        onDismissRequest = {
                            expandedLanguage = false
                            viewModel.dismissLanguageDropdown()
                        }
                    ) {
                        viewModel.languages.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    language = option
                                    viewModel.setLanguage(option)
                                    expandedLanguage = false
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
                        checked = notificationsEnabled && postNotificationPermission.status.isGranted,
                        onCheckedChange = { checked ->
                            notificationsEnabled = checked
                            if (checked) {
                                postNotificationPermission.launchPermissionRequest()
                                if (postNotificationPermission.status.isGranted) {
                                    viewModel.toggleNotifications()
                                }
                            } else {
                                viewModel.toggleNotifications()
                            }
                        },
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
                        checked = darkModeEnabled,
                        onCheckedChange = {
                            darkModeEnabled = it
                            viewModel.toggleDarkMode()
                        },
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
                val activity = LocalContext.current as? android.app.Activity
                Button(
                    onClick = {
                        viewModel.logout {
                            activity?.finishAffinity()
                        }
                    },
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

