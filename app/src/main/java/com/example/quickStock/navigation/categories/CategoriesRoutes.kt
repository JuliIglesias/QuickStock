package com.example.quickStock.navigation.categories

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.quickStock.R

enum class CategoryRoutes(val route: String, private val stringResId: Int) {
    Meat("category/meat", R.string.meat),
    Seafood("category/seafood", R.string.seafood),
    Fruits("category/fruits", R.string.fruits),
    Vegetables("category/vegetables", R.string.vegetables),
    Refrigerator("category/refrigerator", R.string.refrigerator),
    DairyAndEggs("category/dairy_and_eggs", R.string.dairy_and_eggs),
    GrainsAndPasta("category/grains_and_pasta", R.string.grains_and_pasta),
    CannedGoods("category/canned_goods", R.string.canned_goods),
    SpicesAndHerbs("category/spices_and_herbs", R.string.spices_and_herbs),
    Beverages("category/beverages", R.string.beverages),
    Bakery("category/bakery", R.string.bakery),
    Snacks("category/snacks", R.string.snacks),
    Baby("category/baby", R.string.baby),
    Bathroom("category/bathroom", R.string.bathroom),
    Household("category/household", R.string.household),
    PersonalCare("category/personal_care", R.string.personal_care),
    PaperProducts("category/paper_products", R.string.paper_products),
    PetSupplies("category/pet_supplies", R.string.pet_supplies),
    Electronics("category/electronics", R.string.electronics);

    @Composable
    fun getFormattedName(): String {
        return stringResource(id = stringResId)
    }
    // Método para obtener el slug de la ruta (la parte después de "category/")
    fun getRouteSlug(): String {
        return route.substringAfterLast("/")
    }

    companion object {
        // Método para encontrar una categoría por su slug
        fun findBySlug(slug: String): CategoryRoutes? {
            return entries.find { it.getRouteSlug() == slug }
        }
    }
}