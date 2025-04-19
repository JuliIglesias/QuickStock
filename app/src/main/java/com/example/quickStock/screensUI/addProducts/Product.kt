package com.example.quickStock.screensUI.addProducts

data class Product(
    val id: String, //en un futuro se cambiara a lo que escanee con el barcode scanner y la camara del celular
    val name: String,
    val brand: String,
    val category: String,
    val quantityExpirationDate: List<QuantityExpirationDate>,
)

data class QuantityExpirationDate(
    val quantity: Int,
    val expiryDate: String
)