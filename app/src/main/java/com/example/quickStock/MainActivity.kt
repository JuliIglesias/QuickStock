package com.example.quickStock

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import com.example.quickStock.screensUI.navigation.NavBar
import com.example.quickStock.screensUI.navigation.NavHostComposable
import com.example.quickStock.ui.theme.QuickStockTheme
import com.example.quickStock.security.BiometricAuthManager
import com.example.quickStock.auth.AuthManager
import com.example.quickStock.viewModel.userConfig.UserSettingsViewModel
import com.example.quickStock.viewModel.notification.NotificationSchedulerViewModel
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.google.firebase.FirebaseApp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    @Inject
    lateinit var biometricAuthManager: BiometricAuthManager
    @Inject
    lateinit var authManager: AuthManager
    private val userSettingsViewModel: UserSettingsViewModel by viewModels()
    private val notificationSchedulerViewModel: NotificationSchedulerViewModel by viewModels()

    private var isAuthenticated = false
    private var pendingProductId: String? = null
    private var pendingCategoryType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        handleIntent(intent)
        tryShowContent()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (intent != null) {
            handleIntent(intent)
            tryShowContent()
        }
    }

    private fun handleIntent(intent: android.content.Intent) {
        pendingProductId = intent.getStringExtra("product_id")
        pendingCategoryType = intent.getStringExtra("category_type")
    }

    private fun tryShowContent() {
        val biometricManager = androidx.biometric.BiometricManager.from(this)
        val canAuthenticate = biometricManager.canAuthenticate(
            androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG or
                    androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
        )
        if (!isAuthenticated && canAuthenticate == androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS) {
            biometricAuthManager.authenticate(
                this,
                onError = {
                    runOnUiThread {
                        android.widget.Toast.makeText(this,
                            getString(R.string.no_authorized), android.widget.Toast.LENGTH_LONG).show()
                        finish()
                    }
                },
                onSuccess = {
                    isAuthenticated = true
                    runOnUiThread {
                        val currentUser = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser
                        if (currentUser == null) {
                            lifecycleScope.launch {
                                authManager.launchCredentialManager(
                                    onSuccess = { name, email ->
                                        userSettingsViewModel.updateUsername(name)
                                        userSettingsViewModel.updateEmail(email)
                                        userSettingsViewModel.saveSettings()
                                        notificationSchedulerViewModel.scheduleAllProductExpiryNotifications()
                                        showMainContent()
                                    },
                                    onError = { errorMsg ->
                                        runOnUiThread {
                                            android.widget.Toast.makeText(this@MainActivity, errorMsg, android.widget.Toast.LENGTH_LONG).show()
                                            finish()
                                        }
                                    }
                                )
                            }
                        } else {
                            userSettingsViewModel.updateUsername(currentUser.displayName ?: "")
                            userSettingsViewModel.updateEmail(currentUser.email ?: "")
                            userSettingsViewModel.saveSettings()
                            notificationSchedulerViewModel.scheduleAllProductExpiryNotifications()
                            showMainContent()
                        }
                    }
                },
                onFail = {
                    runOnUiThread {
                        android.widget.Toast.makeText(this,
                            getString(R.string.something_went_wrong), android.widget.Toast.LENGTH_LONG).show()
                        finish()
                    }
                }
            )
        } else if (isAuthenticated || canAuthenticate != androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS) {
            notificationSchedulerViewModel.scheduleAllProductExpiryNotifications()
            showMainContent()
        }
    }

    private fun showMainContent() {
        setContent {
            val navController = rememberNavController()
            var navigatedToProduct by remember { mutableStateOf(false) }
            val productId = pendingProductId
            val categoryType = pendingCategoryType

            QuickStockTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavBar(navController::navigate)
                    }
                ) { innerPadding ->
                    if (productId != null && categoryType != null && !navigatedToProduct) {
                        LaunchedEffect(productId, categoryType) {
                            navController.navigate("category/${categoryType}/detail/product/$productId") {
                                popUpTo(0)
                            }
                            navigatedToProduct = true
                            // Limpiar los extras para evitar loops
                            pendingProductId = null
                            pendingCategoryType = null
                        }
                    }
                    NavHostComposable(innerPadding, navController)
                }
            }
        }
    }
}
