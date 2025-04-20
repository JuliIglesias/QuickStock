package com.example.quickStock.model.addProduct

// La clase que representa el estado de la UI
data class ProductFormState(
    val productId: String = "",
    val productName: String = "",
    val productBrand: String = "",
    val productCategory: String = "",
    val productQuantity: Int = 1,
    val productExpiryDate: String = ""
)