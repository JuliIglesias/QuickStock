package com.example.quickStock.model.userConfig

data class UserSettingsState(
    val username: String = "John Doe",
    val email: String = "johndoe@example.com",
    val notificationsEnabled: Boolean = true,
    val darkModeEnabled: Boolean = false,
    val language: String = "English",
    val expandedLanguage: Boolean = false
)