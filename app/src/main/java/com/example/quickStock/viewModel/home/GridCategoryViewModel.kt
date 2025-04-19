package com.example.quickStock.viewModel.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.quickStock.R
import com.example.quickStock.screensUI.navigation.categories.CategoryRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GridCategoryViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {
    // Lista completa de categorías
    private val categories = context.resources.getStringArray(R.array.product_categories)

    // Estado para la consulta de búsqueda
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Estado para las categorías filtradas
    private val _filteredCategories = MutableStateFlow<List<String>>(emptyList())
    val filteredCategories: StateFlow<List<String>> = _filteredCategories.asStateFlow()

    // Evento para cuando se selecciona una categoría
    private val _selectedCategoryRoute = MutableStateFlow<String?>(null)
    val selectedCategoryRoute: StateFlow<String?> = _selectedCategoryRoute.asStateFlow()

    init {
        // Inicializa con todas las categorías
        updateFilteredCategories("")
    }

    // Función para obtener la ruta de una categoría
    fun getCategoryRoute(categoryName: String): String? {
        return CategoryRoutes.entries.find {
            it.getRouteSlug().equals(
                categoryName.replace(" ", "_").replace("&", "and").lowercase(),
                ignoreCase = true
            )
        }?.route
    }

    // Función para actualizar la consulta de búsqueda
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        updateFilteredCategories(query)
    }

    // Función para filtrar categorías según la consulta
    private fun updateFilteredCategories(query: String) {
        _filteredCategories.value = categories
            .filter { it.contains(query, ignoreCase = true) }
    }

    fun onCategorySelected(route: String) {
        _selectedCategoryRoute.value = route
    }

    // Función para reiniciar el estado de selección después de navegar
    fun resetCategorySelection() {
        _selectedCategoryRoute.value = null
    }
}