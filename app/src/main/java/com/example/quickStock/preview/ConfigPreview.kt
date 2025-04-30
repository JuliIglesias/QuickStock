package com.example.quickStock.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.quickStock.R
import com.example.quickStock.model.userConfig.UserSettingsState
import com.example.quickStock.ui.theme.*

@Preview(showBackground = true)
@Composable
fun UserSettingsPagePreview() {
    QuickStockTheme {
        UserSettingsPreviewContent()
    }
}

@Composable
fun UserSettingsPreviewContent() {
    // Datos estáticos para el preview
    val userSettingsState = UserSettingsState()

    val scrollState = rememberScrollState()

    // Usar colores de MaterialTheme directamente
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
        // Encabezado
        Text(
            text = stringResource(id = R.string.user_settings),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        // Tarjeta de Perfil de Usuario
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
                // Campo de Nombre de Usuario
                OutlinedTextField(
                    value = userSettingsState.username,
                    onValueChange = { },
                    label = { Text(stringResource(id = R.string.username)) },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = stringResource(id = R.string.username),
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

                // Campo de Correo Electrónico
                OutlinedTextField(
                    value = userSettingsState.email,
                    onValueChange = { },
                    label = { Text(stringResource(id = R.string.email)) },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = stringResource(id = R.string.email),
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

        // Tarjeta de Preferencias
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
                    text = stringResource(id = R.string.preferences),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Selector de Idioma
                OutlinedTextField(
                    value = userSettingsState.language,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(stringResource(id = R.string.language)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Language,
                            contentDescription = stringResource(id = R.string.language),
                            tint = PrimaryGreen
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = stringResource(id = R.string.language),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryGreen,
                        focusedLabelColor = PrimaryGreen,
                        cursorColor = PrimaryGreen
                    )
                )

                // Activar/Desactivar Notificaciones
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
                            contentDescription = stringResource(id = R.string.notifications),
                            tint = PrimaryGreen
                        )
                        Text(
                            text = stringResource(id = R.string.enable_notifications),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Switch(
                        checked = userSettingsState.notificationsEnabled,
                        onCheckedChange = { },
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

                // Activar/Desactivar Modo Oscuro
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
                            contentDescription = stringResource(id = R.string.dark_mode),
                            tint = PrimaryGreen
                        )
                        Text(
                            text = stringResource(id = R.string.dark_mode),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Switch(
                        checked = userSettingsState.darkModeEnabled,
                        onCheckedChange = { },
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

        // Acciones de Cuenta
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
                    text = stringResource(id = R.string.account),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Botón de Guardar Configuración
                Button(
                    onClick = { /* No hace nada en el preview */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(heightButton),
                    shape = RoundedCornerShape(radiusRound),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
                ) {
                    Icon(
                        Icons.Default.Save,
                        contentDescription = stringResource(id = R.string.save_settings),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.width(spacingSmall))
                    Text(
                        stringResource(id = R.string.save_settings),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                // Botón de Cerrar Sesión
                Button(
                    onClick = { /* No hace nada en el preview */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(heightButton),
                    shape = RoundedCornerShape(radiusRound),
                    colors = ButtonDefaults.buttonColors(containerColor = ErrorRed)
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.Logout,
                        contentDescription = stringResource(id = R.string.logout),
                        tint = White
                    )
                    Spacer(modifier = Modifier.width(spacingSmall))
                    Text(
                        stringResource(id = R.string.logout),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                }
            }
        }
    }
}