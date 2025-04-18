package com.example.quickStock.userConfig

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object DarkModeConfig {
    var darkModeEnabled: Boolean? by mutableStateOf(null)

    fun saveSettings(isEnabled: Boolean?) {
        darkModeEnabled = isEnabled
    }
}