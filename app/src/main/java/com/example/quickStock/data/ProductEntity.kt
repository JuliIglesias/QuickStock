package com.example.quickStock.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.ColumnInfo

@Entity(
    tableName = "products",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Product(
    @PrimaryKey
    val id: String, // Código de barras, alfanumérico
    val name: String,
    val brand: String,
    @ColumnInfo(index = true)
    val categoryId: Long
)

@Entity(
    tableName = "quantity_expiration_dates",
    foreignKeys = [
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class QuantityExpirationDate(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val productId: String,
    val quantity: Int,
    val expiryDate: String
)
