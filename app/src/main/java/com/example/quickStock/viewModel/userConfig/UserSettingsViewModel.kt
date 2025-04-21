package com.example.quickStock.viewModel.userConfig

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickStock.model.userConfig.UserSettingsState
import com.example.quickStock.screensUI.userConfig.DarkModeConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserSettingsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UserSettingsState())
    val uiState: StateFlow<UserSettingsState> = _uiState.asStateFlow()

    val languages = listOf("English", "Spanish", "French", "German")

    init {
        // Inicializa el dark mode desde las preferencias guardadas
        val systemDarkTheme = false // Obtendrías esto del contexto en una implementación real
        _uiState.update { currentState ->
            currentState.copy(
                darkModeEnabled = DarkModeConfig.darkModeEnabled ?: systemDarkTheme
            )
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
        val newDarkModeState = !_uiState.value.darkModeEnabled
        _uiState.update { currentState ->
            currentState.copy(darkModeEnabled = newDarkModeState)
        }
        DarkModeConfig.saveSettings(newDarkModeState)
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
            // Implementa la lógica de cierre de sesión
            // Por ejemplo: authRepository.logout()
        }
    }

    fun updateEmail(newEmail: String) {
        _uiState.update { currentState ->
            currentState.copy(email = newEmail)
        }
    }
}