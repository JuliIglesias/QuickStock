package com.example.quickStock.mocking

import com.example.quickStock.addProducts.Product

fun getProductsByCategory(category: String): List<Product> {
    return when (category) {
        "Meat" -> listOf(
            Product(id = "1", name = "Lamb", brand = "Brand A", category = "Meat", quantity = 10, expiryDate = "2024-01-01"),
            Product(id = "2", name = "Beef", brand = "Brand B", category = "Meat", quantity = 5, expiryDate = "2024-02-01"),
            Product(id = "3", name = "Pork", brand = "Brand C", category = "Meat", quantity = 8, expiryDate = "2024-03-01")
        )
        "Seafood" -> listOf(
            Product(id = "4", name = "Salmon", brand = "Brand D", category = "Seafood", quantity = 12, expiryDate = "2024-04-01"),
            Product(id = "5", name = "Shrimp", brand = "Brand E", category = "Seafood", quantity = 20, expiryDate = "2024-05-01"),
            Product(id = "6", name = "Tuna", brand = "Brand F", category = "Seafood", quantity = 15, expiryDate = "2024-06-01")
        )
        "Fruits" -> listOf(
            Product(id = "7", name = "Apples", brand = "Brand G", category = "Fruits", quantity = 25, expiryDate = "2024-07-01"),
            Product(id = "8", name = "Bananas", brand = "Brand H", category = "Fruits", quantity = 30, expiryDate = "2024-08-01"),
            Product(id = "9", name = "Oranges", brand = "Brand I", category = "Fruits", quantity = 18, expiryDate = "2024-09-01")
        )
        "Vegetables" -> listOf(
            Product(id = "10", name = "Carrots", brand = "Brand J", category = "Vegetables", quantity = 22, expiryDate = "2024-10-01"),
            Product(id = "11", name = "Tomatoes", brand = "Brand K", category = "Vegetables", quantity = 16, expiryDate = "2024-11-01"),
            Product(id = "12", name = "Spinach", brand = "Brand L", category = "Vegetables", quantity = 10, expiryDate = "2024-12-01")
        )
        "Refrigerator" -> listOf(
            Product(id = "13", name = "Milk", brand = "Brand M", category = "Refrigerator", quantity = 12, expiryDate = "2024-01-15"),
            Product(id = "14", name = "Butter", brand = "Brand N", category = "Refrigerator", quantity = 8, expiryDate = "2024-02-15"),
            Product(id = "15", name = "Yogurt", brand = "Brand O", category = "Refrigerator", quantity = 10, expiryDate = "2024-03-15")
        )
        "Dairy & Eggs" -> listOf(
            Product(id = "16", name = "Eggs", brand = "Brand P", category = "Dairy & Eggs", quantity = 30, expiryDate = "2024-04-15"),
            Product(id = "17", name = "Cheese", brand = "Brand Q", category = "Dairy & Eggs", quantity = 12, expiryDate = "2024-05-15"),
            Product(id = "18", name = "Cream", brand = "Brand R", category = "Dairy & Eggs", quantity = 6, expiryDate = "2024-06-15")
        )
        "Grains & Pasta" -> listOf(
            Product(id = "19", name = "Rice", brand = "Brand S", category = "Grains & Pasta", quantity = 50, expiryDate = "2024-07-15"),
            Product(id = "20", name = "Spaghetti", brand = "Brand T", category = "Grains & Pasta", quantity = 40, expiryDate = "2024-08-15"),
            Product(id = "21", name = "Quinoa", brand = "Brand U", category = "Grains & Pasta", quantity = 20, expiryDate = "2024-09-15")
        )
        "Canned Goods" -> listOf(
            Product(id = "22", name = "Canned Beans", brand = "Brand V", category = "Canned Goods", quantity = 15, expiryDate = "2024-10-15"),
            Product(id = "23", name = "Canned Tomatoes", brand = "Brand W", category = "Canned Goods", quantity = 18, expiryDate = "2024-11-15"),
            Product(id = "24", name = "Canned Tuna", brand = "Brand X", category = "Canned Goods", quantity = 12, expiryDate = "2024-12-15")
        )
        "Spices & Herbs" -> listOf(
            Product(id = "25", name = "Oregano", brand = "Brand Y", category = "Spices & Herbs", quantity = 10, expiryDate = "2024-01-20"),
            Product(id = "26", name = "Pepper", brand = "Brand Z", category = "Spices & Herbs", quantity = 8, expiryDate = "2024-02-20"),
            Product(id = "27", name = "Cinnamon", brand = "Brand AA", category = "Spices & Herbs", quantity = 6, expiryDate = "2024-03-20")
        )
        "Beverages" -> listOf(
            Product(id = "28", name = "Water", brand = "Brand AB", category = "Beverages", quantity = 100, expiryDate = "2024-04-20"),
            Product(id = "29", name = "Orange Juice", brand = "Brand AC", category = "Beverages", quantity = 50, expiryDate = "2024-05-20"),
            Product(id = "30", name = "Soda", brand = "Brand AD", category = "Beverages", quantity = 60, expiryDate = "2024-06-20")
        )
        "Bakery" -> listOf(
            Product(id = "31", name = "Bread", brand = "Brand AE", category = "Bakery", quantity = 20, expiryDate = "2024-07-20"),
            Product(id = "32", name = "Cakes", brand = "Brand AF", category = "Bakery", quantity = 10, expiryDate = "2024-08-20"),
            Product(id = "33", name = "Cookies", brand = "Brand AG", category = "Bakery", quantity = 15, expiryDate = "2024-09-20")
        )
        "Snacks" -> listOf(
            Product(id = "34", name = "Chips", brand = "Brand AH", category = "Snacks", quantity = 25, expiryDate = "2024-10-20"),
            Product(id = "35", name = "Chocolate", brand = "Brand AI", category = "Snacks", quantity = 30, expiryDate = "2024-11-20"),
            Product(id = "36", name = "Granola Bars", brand = "Brand AJ", category = "Snacks", quantity = 20, expiryDate = "2024-12-20")
        )
        "Baby" -> listOf(
            Product(id = "37", name = "Baby Formula", brand = "Brand AK", category = "Baby", quantity = 10, expiryDate = "2024-01-25"),
            Product(id = "38", name = "Baby Wipes", brand = "Brand AL", category = "Baby", quantity = 15, expiryDate = "2024-02-25"),
            Product(id = "39", name = "Diapers", brand = "Brand AM", category = "Baby", quantity = 20, expiryDate = "2024-03-25")
        )
        "Bathroom" -> listOf(
            Product(id = "40", name = "Toilet Paper", brand = "Brand AN", category = "Bathroom", quantity = 50, expiryDate = "2024-04-25"),
            Product(id = "41", name = "Soap", brand = "Brand AO", category = "Bathroom", quantity = 30, expiryDate = "2024-05-25"),
            Product(id = "42", name = "Shampoo", brand = "Brand AP", category = "Bathroom", quantity = 20, expiryDate = "2024-06-25")
        )
        "Household" -> listOf(
            Product(id = "43", name = "Detergent", brand = "Brand AQ", category = "Household", quantity = 15, expiryDate = "2024-07-25"),
            Product(id = "44", name = "Sponges", brand = "Brand AR", category = "Household", quantity = 25, expiryDate = "2024-08-25"),
            Product(id = "45", name = "All-Purpose Cleaner", brand = "Brand AS", category = "Household", quantity = 10, expiryDate = "2024-09-25")
        )
        "Personal Care" -> listOf(
            Product(id = "46", name = "Toothpaste", brand = "Brand AT", category = "Personal Care", quantity = 20, expiryDate = "2024-10-25"),
            Product(id = "47", name = "Deodorant", brand = "Brand AU", category = "Personal Care", quantity = 15, expiryDate = "2024-11-25"),
            Product(id = "48", name = "Moisturizer", brand = "Brand AV", category = "Personal Care", quantity = 10, expiryDate = "2024-12-25")
        )
        "Paper Products" -> listOf(
            Product(id = "49", name = "Napkins", brand = "Brand AW", category = "Paper Products", quantity = 30, expiryDate = "2024-01-30"),
            Product(id = "50", name = "Paper Towels", brand = "Brand AX", category = "Paper Products", quantity = 20, expiryDate = "2024-02-30"),
            Product(id = "51", name = "Card stock", brand = "Brand AY", category = "Paper Products", quantity = 10, expiryDate = "2024-03-30")
        )
        "Pet Supplies" -> listOf(
            Product(id = "52", name = "Dog Food", brand = "Brand AZ", category = "Pet Supplies", quantity = 25, expiryDate = "2024-04-30"),
            Product(id = "53", name = "Cat Food", brand = "Brand BA", category = "Pet Supplies", quantity = 20, expiryDate = "2024-05-30"),
            Product(id = "54", name = "Cat Litter", brand = "Brand BB", category = "Pet Supplies", quantity = 15, expiryDate = "2024-06-30")
        )
        "Electronics" -> listOf(
            Product(id = "55", name = "Mobile Phone", brand = "Brand BC", category = "Electronics", quantity = 5, expiryDate = "2024-07-30"),
            Product(id = "56", name = "Headphones", brand = "Brand BD", category = "Electronics", quantity = 10, expiryDate = "2024-08-30"),
            Product(id = "57", name = "Keyboard", brand = "Brand BE", category = "Electronics", quantity = 8, expiryDate = "2024-09-30")
        )
        else -> emptyList()
    }
}