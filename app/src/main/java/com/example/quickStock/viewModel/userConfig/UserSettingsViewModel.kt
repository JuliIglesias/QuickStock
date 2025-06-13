package com.example.quickStock.viewModel.userConfig

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickStock.model.userConfig.UserSettingsState
import com.example.quickStock.storage.PreferencesKeys
import com.example.quickStock.storage.getFromDataStore
import com.example.quickStock.storage.saveToDataStore
import com.example.quickStock.screensUI.userConfig.DarkModeConfig
import com.example.quickStock.auth.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserSettingsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val authManager: AuthManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(UserSettingsState())
    val uiState: StateFlow<UserSettingsState> = _uiState.asStateFlow()

    val languages = listOf("English", "Spanish", "French", "German")

    init {
        viewModelScope.launch {
            val darkMode = getFromDataStore(context, PreferencesKeys.DARK_MODE_KEY).first() ?: false
            DarkModeConfig.saveSettings(darkMode) // Sincroniza al iniciar
            val notifications = getFromDataStore(context, PreferencesKeys.NOTIFICATIONS_KEY).first() ?: true
            val username = getFromDataStore(context, PreferencesKeys.USER_NAME_KEY).first() ?: "John Doe"
            _uiState.update { currentState ->
                currentState.copy(
                    darkModeEnabled = darkMode,
                    notificationsEnabled = notifications,
                    username = username
                )
            }
        }
    }

    fun updateUsername(newUsername: String) {
        _uiState.update { currentState ->
            currentState.copy(username = newUsername)
        }
    }

    fun toggleNotifications() {
        _uiState.update { currentState ->
            currentState.copy(notificationsEnabled = !currentState.notificationsEnabled)
        }
    }

    fun toggleDarkMode() {
        _uiState.update { currentState ->
            val newValue = !currentState.darkModeEnabled
            DarkModeConfig.saveSettings(newValue) // Sincroniza al cambiar
            currentState.copy(darkModeEnabled = newValue)
        }
    }

    fun setLanguage(language: String) {
        if (languages.contains(language)) {
            _uiState.update { currentState ->
                currentState.copy(language = language)
            }
        }
    }

    fun toggleLanguageDropdown() {
        _uiState.update { currentState ->
            currentState.copy(expandedLanguage = !currentState.expandedLanguage)
        }
    }

    fun dismissLanguageDropdown() {
        _uiState.update { currentState ->
            currentState.copy(expandedLanguage = false)
        }
    }

    fun logout() {
        viewModelScope.launch {
            authManager.signOut()
            _uiState.update { currentState ->
                currentState.copy(
                    username = "",
                    email = "",
                    // Puedes limpiar otros campos si lo deseas
                )
            }
        }
    }

    fun updateEmail(newEmail: String) {
        _uiState.update { currentState ->
            currentState.copy(email = newEmail)
        }
    }

    fun saveSettings() {
        viewModelScope.launch {
            saveToDataStore(context, _uiState.value.darkModeEnabled, PreferencesKeys.DARK_MODE_KEY)
            DarkModeConfig.saveSettings(_uiState.value.darkModeEnabled) // Sincroniza al guardar
            saveToDataStore(context, _uiState.value.notificationsEnabled, PreferencesKeys.NOTIFICATIONS_KEY)
            saveToDataStore(context, _uiState.value.username, PreferencesKeys.USER_NAME_KEY)
        }
    }
}

