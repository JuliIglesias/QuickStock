package com.example.quickStock.userConfig

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object DarkModeConfig {
    fun saveSettings() {
        darkModeEnabled = !darkModeEnabled
    }

    var darkModeEnabled by mutableStateOf(false)
}