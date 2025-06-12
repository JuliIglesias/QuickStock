package com.example.quickStock.di

import android.content.Context
import com.example.quickStock.data.ProductDao
import com.example.quickStock.data.CategoryDao
import com.example.quickStock.data.QuantityExpirationDateDao
import com.example.quickStock.storage.QuickStockDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): QuickStockDatabase =
        QuickStockDatabase.getDatabase(context)

    @Provides
    fun provideProductDao(db: QuickStockDatabase): ProductDao = db.productDao()

    @Provides
    fun provideCategoryDao(db: QuickStockDatabase): CategoryDao = db.categoryDao()

    @Provides
    fun provideQuantityExpirationDateDao(db: QuickStockDatabase): QuantityExpirationDateDao = db.quantityExpirationDateDao()
}

