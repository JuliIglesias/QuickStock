package com.example.quickStock.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quickStock.data.Product
import com.example.quickStock.data.ProductDao


@Database(entities = [Product::class], version = 1)
abstract class QuickStockDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: QuickStockDatabase? = null

        fun getDatabase(context: Context): QuickStockDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuickStockDatabase::class.java,
                    "quick_stock_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}