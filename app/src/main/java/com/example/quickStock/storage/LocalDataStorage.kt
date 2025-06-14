package com.example.quickStock.storage

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "QUICK_STOCK_DATA_STORE")

object PreferencesKeys {
    val USER_NAME_KEY = stringPreferencesKey("user_name_key")
    val DARK_MODE_KEY = booleanPreferencesKey("dark_mode_key")
    val NOTIFICATIONS_KEY = booleanPreferencesKey("notifications_key")
    val EMAIL_KEY = stringPreferencesKey("email_key")
    val LANGUAGE_KEY = stringPreferencesKey("language_key")
    val EXPANDED_LANGUAGE_KEY = booleanPreferencesKey("expanded_language_key")
}

suspend fun <T> saveToDataStore(context: Context, value: T, key: Preferences.Key<T>) {
    context.dataStore.edit { preferences ->
        preferences[key] = value
    }
}

fun <T> getFromDataStore(context: Context, key: Preferences.Key<T>): Flow<T?> {
    return context.dataStore.data
        .map { preferences ->
            preferences[key]
        }
}

