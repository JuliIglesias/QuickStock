package com.example.quickStock

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import com.example.quickStock.screensUI.navigation.NavBar
import com.example.quickStock.screensUI.navigation.NavHostComposable
import com.example.quickStock.ui.theme.QuickStockTheme
import com.example.quickStock.security.BiometricAuthManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    @Inject
    lateinit var biometricAuthManager: BiometricAuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val biometricManager = androidx.biometric.BiometricManager.from(this)
        val canAuthenticate = biometricManager.canAuthenticate(
            androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG or
                    androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
        )
        if (canAuthenticate == androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS) {
            biometricAuthManager.authenticate(
                this,
                onError = {
                    runOnUiThread {
                        android.widget.Toast.makeText(this, "geda gedi registrate", android.widget.Toast.LENGTH_LONG).show()
                        finish()
                    }
                },
                onSuccess = {
                    runOnUiThread {
                        setContent {
                            val navController = rememberNavController()
                            QuickStockTheme {
                                Scaffold(
                                    modifier = Modifier.fillMaxSize(),
                                    bottomBar = {
                                        NavBar(navController::navigate)
                                    }
                                ) { innerPadding ->
                                    NavHostComposable(innerPadding, navController)
                                }
                            }
                        }
                    }
                },
                onFail = {
                    runOnUiThread {
                        android.widget.Toast.makeText(this, "geda gedi registrate", android.widget.Toast.LENGTH_LONG).show()
                        finish()
                    }
                }
            )
        } else {
            // Si no hay biométrico, deja pasar y muestra la app normalmente
            setContent {
                val navController = rememberNavController()
                QuickStockTheme {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            NavBar(navController::navigate)
                        }
                    ) { innerPadding ->
                        NavHostComposable(innerPadding, navController)
                    }
                }
            }
        }
    }
}
