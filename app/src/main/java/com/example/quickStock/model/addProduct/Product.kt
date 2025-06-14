package com.example.quickStock.model.addProduct

data class Product(
    val id: String,
    val name: String,
    val brand: String,
    val category: String,
    val quantityExpirationDate: List<QuantityExpirationDate>,
)

data class QuantityExpirationDate(
    val quantity: Int,
    val expiryDate: String
)