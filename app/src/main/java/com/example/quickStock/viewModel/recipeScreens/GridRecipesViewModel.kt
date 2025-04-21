package com.example.quickStock.viewModel.recipeScreens

import androidx.lifecycle.ViewModel
import com.example.quickStock.model.recipe.RecipeButtonData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GridRecipesViewModel : ViewModel() {

    // Estado para la búsqueda
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Lista completa de recetas
    private val _allRecipes = MutableStateFlow<List<RecipeButtonData>>(emptyList())

    // Lista filtrada de recetas (lo que se muestra)
    private val _filteredRecipes = MutableStateFlow<List<RecipeButtonData>>(emptyList())
    val filteredRecipes: StateFlow<List<RecipeButtonData>> = _filteredRecipes.asStateFlow()

    // Lista de resultados de búsqueda (solo títulos)
    private val _searchResults = MutableStateFlow<List<String>>(emptyList())
    val searchResults: StateFlow<List<String>> = _searchResults.asStateFlow()

    // Inicializar el ViewModel con la lista de recetas
    fun setRecipes(recipes: List<RecipeButtonData>) {
        _allRecipes.value = recipes
        _filteredRecipes.value = recipes
        updateSearchResults()
    }

    // Actualizar la consulta de búsqueda
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        filterRecipes()
        updateSearchResults()
    }

    // Ejecutar la búsqueda
    fun performSearch() {
        filterRecipes()
    }

    // Filtrar las recetas según la consulta actual
    private fun filterRecipes() {
        if (_searchQuery.value.isBlank()) {
            _filteredRecipes.value = _allRecipes.value
        } else {
            _filteredRecipes.value = _allRecipes.value.filter {
                it.title.contains(_searchQuery.value, ignoreCase = true)
            }
        }
    }

    // Actualizar los resultados de búsqueda (sugerencias)
    private fun updateSearchResults() {
        _searchResults.value = _allRecipes.value
            .filter { it.title.contains(_searchQuery.value, ignoreCase = true) }
            .map { it.title }
    }
}