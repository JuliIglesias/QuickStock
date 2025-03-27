package com.example.quickStock.addProducts

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val brand: String,
    val category: String,
    val quantity: Int
)