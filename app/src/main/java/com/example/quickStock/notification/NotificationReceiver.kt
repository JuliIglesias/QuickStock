package com.example.quickStock.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.quickStock.MainActivity
import com.example.quickStock.R
import kotlin.random.Random

const val notificationChannelID = "quick_stock_notification_channel"

// BroadcastReceiver for handling notifications
class NotificationReceiver : BroadcastReceiver() {

    // Method called when the broadcast is received
    override fun onReceive(context: Context, intent: Intent) {

        val productName = intent.getStringExtra(context.getString(R.string.product_name)) ?: context.getString(R.string.producto)
        val expiryDate = intent.getStringExtra(context.getString(R.string.expiry_date)) ?: ""
        val daysBefore = intent.getIntExtra(context.getString(R.string.days_before), -1)
        val productId = intent.getStringExtra("product_id")

        val openAppIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            if (productId != null) putExtra("product_id", productId)
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, openAppIntent, PendingIntent.FLAG_IMMUTABLE)

        val notificationManager = context.getSystemService(NotificationManager::class.java)

        val (title, text) = when (daysBefore) {
            7 -> context.getString(R.string.atencion) to context.getString(
                R.string.vencimiento_7_dias,
                productName,
                expiryDate
            )
            3 -> context.getString(R.string.atencion) to context.getString(
                R.string.vencimiento_3_dias,
                productName,
                expiryDate
            )
            0 -> context.getString(R.string.vence_hoy) to context.getString(
                R.string.vencimiento_0_dias_HOY,
                productName,
                expiryDate
            )
            else -> context.getString(R.string.producto_por_vencer) to context.getString(
                R.string.producto_proximo_a_vencer,
                productName,
                expiryDate
            )
        }

        val notification = NotificationCompat.Builder(context, notificationChannelID)
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(R.drawable.ic_launcher_foreground) // Icono válido obligatorio
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(Random.nextInt(), notification)
    }
}


