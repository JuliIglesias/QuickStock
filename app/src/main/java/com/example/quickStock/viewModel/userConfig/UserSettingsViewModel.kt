package com.example.quickStock.viewModel.userConfig

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickStock.R
import com.example.quickStock.storage.PreferencesKeys
import com.example.quickStock.storage.getFromDataStore
import com.example.quickStock.storage.saveToDataStore
import com.example.quickStock.screensUI.userConfig.DarkModeConfig
import com.example.quickStock.auth.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserSettingsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val authManager: AuthManager
) : ViewModel() {
    val languages = listOf(context.getString(R.string.english),
        context.getString(R.string.spanish),
        context.getString(R.string.french),
        context.getString(R.string.german))

    suspend fun getUsername(): String {
        return getFromDataStore(context, PreferencesKeys.USER_NAME_KEY).first() ?: context.getString(
            R.string.nothing_String
        )
    }

    suspend fun getEmail(): String {
        return getFromDataStore(context, PreferencesKeys.EMAIL_KEY).first() ?: context.getString(
            R.string.nothing_String
        )
    }

    suspend fun getNotificationsEnabled(): Boolean {
        return getFromDataStore(context, PreferencesKeys.NOTIFICATIONS_KEY).first() != false
    }

    suspend fun getDarkModeEnabled(): Boolean {
        return getFromDataStore(context, PreferencesKeys.DARK_MODE_KEY).first() == true
    }

    suspend fun getLanguage(): String {
        return getFromDataStore(context, PreferencesKeys.LANGUAGE_KEY).first() ?: context.getString(
            R.string.english
        )
    }

    suspend fun getExpandedLanguage(): Boolean {
        return getFromDataStore(context, PreferencesKeys.EXPANDED_LANGUAGE_KEY).first() == true
    }

    fun updateUsername(newUsername: String) {
        viewModelScope.launch {
            saveToDataStore(context, newUsername, PreferencesKeys.USER_NAME_KEY)
        }
    }

    fun updateEmail(newEmail: String) {
        viewModelScope.launch {
            saveToDataStore(context, newEmail, PreferencesKeys.EMAIL_KEY)
        }
    }

    fun toggleNotifications() {
        viewModelScope.launch {
            val current = getNotificationsEnabled()
            saveToDataStore(context, !current, PreferencesKeys.NOTIFICATIONS_KEY)
        }
    }

    fun toggleDarkMode() {
        viewModelScope.launch {
            val current = getDarkModeEnabled()
            saveToDataStore(context, !current, PreferencesKeys.DARK_MODE_KEY)
            DarkModeConfig.saveSettings(!current)
        }
    }

    fun setLanguage(language: String) {
        if (languages.contains(language)) {
            viewModelScope.launch {
                saveToDataStore(context, language, PreferencesKeys.LANGUAGE_KEY)
            }
        }
    }

    fun toggleLanguageDropdown() {
        viewModelScope.launch {
            val current = getExpandedLanguage()
            saveToDataStore(context, !current, PreferencesKeys.EXPANDED_LANGUAGE_KEY)
        }
    }

    fun dismissLanguageDropdown() {
        viewModelScope.launch {
            saveToDataStore(context, false, PreferencesKeys.EXPANDED_LANGUAGE_KEY)
        }
    }

    fun clearUserDataFromLocalStorage() {
        viewModelScope.launch {
            saveToDataStore(context, context.getString(
                R.string.nothing_String
            ), PreferencesKeys.USER_NAME_KEY)
            saveToDataStore(context, context.getString(
                R.string.nothing_String
            ), PreferencesKeys.EMAIL_KEY)
        }
    }

    fun logout(onLogout: (() -> Unit)? = null) {
        viewModelScope.launch {
            authManager.signOut()
            clearUserDataFromLocalStorage()
            onLogout?.invoke()
        }
    }

    fun updateUserFromAuthManager() {
        val (name, email) = authManager.getCurrentUserInfo()
        updateUsername(name)
        updateEmail(email)
    }

    fun saveSettings() {
        // Ya se guardan los settings en cada setter
    }

    fun initDarkModeIfNeeded(systemDarkMode: Boolean) {
        viewModelScope.launch {
            val darkModePref = getDarkModeEnabled()
            if (!darkModePref) {
                saveToDataStore(context, systemDarkMode, PreferencesKeys.DARK_MODE_KEY)
                DarkModeConfig.saveSettings(systemDarkMode)
            }
        }
    }
}
