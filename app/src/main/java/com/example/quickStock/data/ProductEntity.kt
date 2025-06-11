package com.example.quickStock.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: String = "",
    val name: String,
    val brand: String,
    val category: String,
    val quantityExpirationDate: List<QuantityExpirationDate>,
)

@Entity(tableName = "quantityExpirationDate")
data class QuantityExpirationDate(
    @PrimaryKey(autoGenerate = true)
    val quantity: Int,
    val expiryDate: String
)
