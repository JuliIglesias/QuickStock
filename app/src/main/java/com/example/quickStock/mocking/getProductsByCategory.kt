package com.example.quickStock.mocking

import com.example.quickStock.model.addProduct.Product
import com.example.quickStock.model.addProduct.QuantityExpirationDate


fun getProductsByCategory(category: String): List<Product> {
    return when (category.lowercase()) {
        "meat" -> listOf(
            Product(
                id = "1",
                name = "Lamb",
                brand = "Brand A",
                category = "Meat",
                quantityExpirationDate = listOf(
                    QuantityExpirationDate(quantity = 5, expiryDate = "2024-01-01"),
                    QuantityExpirationDate(quantity = 5, expiryDate = "2024-01-15")
                )
            ),
            Product(
                id = "2",
                name = "Beef",
                brand = "Brand B",
                category = "Meat",
                quantityExpirationDate = listOf(
                    QuantityExpirationDate(quantity = 3, expiryDate = "2024-02-01"),
                    QuantityExpirationDate(quantity = 2, expiryDate = "2024-02-15")
                )
            ),
            Product(
                id = "3",
                name = "Pork",
                brand = "Brand C",
                category = "Meat",
                quantityExpirationDate = listOf(
                    QuantityExpirationDate(quantity = 4, expiryDate = "2024-03-01"),
                    QuantityExpirationDate(quantity = 4, expiryDate = "2024-03-15")
                )
            )
        )
        "seafood" -> listOf(
            Product(
                id = "4",
                name = "Salmon",
                brand = "Brand D",
                category = "Seafood",
                quantityExpirationDate = listOf(
                    QuantityExpirationDate(quantity = 6, expiryDate = "2024-04-01"),
                    QuantityExpirationDate(quantity = 6, expiryDate = "2024-04-15")
                )
            ),
            Product(
                id = "5",
                name = "Shrimp",
                brand = "Brand E",
                category = "Seafood",
                quantityExpirationDate = listOf(
                    QuantityExpirationDate(quantity = 10, expiryDate = "2024-05-01"),
                    QuantityExpirationDate(quantity = 10, expiryDate = "2024-05-15")
                )
            ),
            Product(
                id = "6",
                name = "Tuna",
                brand = "Brand F",
                category = "Seafood",
                quantityExpirationDate = listOf(
                    QuantityExpirationDate(quantity = 8, expiryDate = "2024-06-01"),
                    QuantityExpirationDate(quantity = 7, expiryDate = "2024-06-15")
                )
            )
        )
        "fruits" -> listOf(
            Product(
                id = "7",
                name = "Apple",
                brand = "Brand G",
                category = "Fruits",
                quantityExpirationDate = listOf(
                    QuantityExpirationDate(quantity = 10, expiryDate = "2024-07-01"),
                    QuantityExpirationDate(quantity = 5, expiryDate = "2024-07-15")
                )
            ),
            Product(
                id = "8",
                name = "Banana",
                brand = "Brand H",
                category = "Fruits",
                quantityExpirationDate = listOf(
                    QuantityExpirationDate(quantity = 12, expiryDate = "2024-08-01"),
                    QuantityExpirationDate(quantity = 8, expiryDate = "2024-08-15")
                )
            ),
            Product(
                id = "9",
                name = "Orange",
                brand = "Brand I",
                category = "Fruits",
                quantityExpirationDate = listOf(
                    QuantityExpirationDate(quantity = 15, expiryDate = "2024-09-01"),
                    QuantityExpirationDate(quantity = 10, expiryDate = "2024-09-15")
                )
            )
        )
        "vegetables" -> listOf(
            Product(
                id = "10",
                name = "Carrot",
                brand = "Brand J",
                category = "Vegetables",
                quantityExpirationDate = listOf(
                    QuantityExpirationDate(quantity = 8, expiryDate = "2024-10-01"),
                    QuantityExpirationDate(quantity = 6, expiryDate = "2024-10-15")
                )
            ),
            Product(
                id = "11",
                name = "Potato",
                brand = "Brand K",
                category = "Vegetables",
                quantityExpirationDate = listOf(
                    QuantityExpirationDate(quantity = 20, expiryDate = "2024-11-01"),
                    QuantityExpirationDate(quantity = 15, expiryDate = "2024-11-15")
                )
            ),
            Product(
                id = "12",
                name = "Tomato",
                brand = "Brand L",
                category = "Vegetables",
                quantityExpirationDate = listOf(
                    QuantityExpirationDate(quantity = 10, expiryDate = "2024-12-01"),
                    QuantityExpirationDate(quantity = 8, expiryDate = "2024-12-15")
                )
            )
        )

        // Continúa con el resto de las categorías siguiendo el mismo formato
        else -> emptyList()
    }
}

