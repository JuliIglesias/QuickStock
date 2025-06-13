package com.example.quickStock.viewModel.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickStock.R
import com.example.quickStock.data.ProductDao
import com.example.quickStock.data.QuantityExpirationDateDao
import com.example.quickStock.notification.NotificationReceiver
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class NotificationSchedulerViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val productDao: ProductDao,
    private val quantityExpirationDateDao: QuantityExpirationDateDao
) : ViewModel() {

    fun scheduleAllProductExpiryNotifications() {
        viewModelScope.launch {
            val products = productDao.getAllProducts()
            val formatter = DateTimeFormatter.ofPattern(context.getString(R.string.yyyy_mm_dd))
            for (product in products) {
                val expiries = quantityExpirationDateDao.getByProductId(product.id)
                for (qed in expiries) {
                    val expiryDate = try {
                        LocalDate.parse(qed.expiryDate, formatter)
                    } catch (e: Exception) {
                        continue
                    }
                    val now = LocalDate.now()
                    val notifyDays = listOf(7, 3, 0)
                    for (daysBefore in notifyDays) {
                        val notifyDate = expiryDate.minusDays(daysBefore.toLong())
                        if (notifyDate.isBefore(now)) continue // No notificar fechas pasadas
                        scheduleNotification(
                            productName = product.name,
                            expiryDate = expiryDate.toString(),
                            daysBefore = daysBefore,
                            triggerDate = notifyDate
                        )
                    }
                }
            }
        }
    }

    private fun scheduleNotification(productName: String, expiryDate: String, daysBefore: Int, triggerDate: LocalDate) {
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra(context.getString(R.string.product_name), productName)
            putExtra(context.getString(R.string.expiry_date), expiryDate)
            putExtra(context.getString(R.string.days_before), daysBefore)
        }
        val requestCode = (productName + expiryDate + daysBefore).hashCode()
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val triggerMillis = triggerDate.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli()
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            triggerMillis,
            pendingIntent
        )
    }
}

