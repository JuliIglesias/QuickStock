package com.example.quickStock.viewModel.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickStock.R
import com.example.quickStock.apiManager.ApiServiceImpl
import com.example.quickStock.data.CategoryDao
import com.example.quickStock.data.ProductDao
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GridCategoryViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val apiServiceImpl: ApiServiceImpl,
    private val productDao: ProductDao,
    private val categoryDao: CategoryDao
) : ViewModel() {
    // Lista completa de categorías fijas
    private val categories = context.resources.getStringArray(R.array.product_categories).toList()

    // Estado para la consulta de búsqueda
    private val _searchQuery = MutableStateFlow(context.getString(
        R.string.nothing_String
    ))
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Estado para las categorías con/sin productos
    private val _categoriesWithProducts = MutableStateFlow<List<String>>(emptyList())
    val categoriesWithProducts: StateFlow<List<String>> = _categoriesWithProducts.asStateFlow()
    private val _categoriesWithoutProducts = MutableStateFlow<List<String>>(emptyList())
    val categoriesWithoutProducts: StateFlow<List<String>> = _categoriesWithoutProducts.asStateFlow()

    // Evento para cuando se selecciona una categoría
    private val _selectedCategoryRoute = MutableStateFlow<String?>(null)
    val selectedCategoryRoute: StateFlow<String?> = _selectedCategoryRoute.asStateFlow()

    init {
        updateCategoryLists(context.getString(
            R.string.nothing_String
        ))
    }

    fun getCategoryRoute(categoryName: String): String {
        return "category/${categoryName.replace(" ", "_").replace("&", "and").lowercase()}"
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        updateCategoryLists(query)
    }

    private fun updateCategoryLists(query: String) {
        viewModelScope.launch {
            val allProducts = productDao.getAllProducts()
            val categoriesWith = categories.filter { cat ->
                allProducts.any { prod ->
                    val catEntity = categoryDao.getCategoryById(prod.categoryId)
                    catEntity?.name.equals(cat, ignoreCase = true)
                }
            }.filter { it.contains(query, ignoreCase = true) }.sortedBy { it.lowercase() }
            val categoriesWithout = categories.filter { it !in categoriesWith }
                .filter { it.contains(query, ignoreCase = true) }
                .sortedBy { it.lowercase() }
            _categoriesWithProducts.value = categoriesWith
            _categoriesWithoutProducts.value = categoriesWithout
        }
    }

    fun onCategorySelected(route: String) {
        _selectedCategoryRoute.value = route
    }

    fun resetCategorySelection() {
        _selectedCategoryRoute.value = null
    }
}

