package com.example.quickStock.viewModel.recipeScreens

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.quickStock.apiManager.ApiServiceImpl
import com.example.quickStock.model.recipe.RecipeListData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val apiServiceImpl: ApiServiceImpl
) : ViewModel() {

    // Estados para la búsqueda
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _filteredRecipes = MutableStateFlow<List<RecipeListData>>(emptyList())
    val filteredRecipes: StateFlow<List<RecipeListData>> = _filteredRecipes.asStateFlow()

    private val _searchResults = MutableStateFlow<List<String>>(emptyList())
    val searchResults: StateFlow<List<String>> = _searchResults.asStateFlow()

    // Estado para el título de la pantalla
    private val _screenTitle = MutableStateFlow("")
    val screenTitle: StateFlow<String> = _screenTitle.asStateFlow()

    // Estado para la lista de recetas
    private val _recipes = MutableStateFlow<List<RecipeListData>>(emptyList())
    val recipes: StateFlow<List<RecipeListData>> = _recipes.asStateFlow()

    // Loading state
    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    // Retry state
    private val _showRetry = MutableStateFlow(false)
    val showRetry = _showRetry.asStateFlow()

    // Cargar recetas según el tipo
    fun loadRecipesByCategory(recipeType: String, onRecipeClick: (String) -> Unit) {
        _loading.value = true
        _showRetry.value = false
        _screenTitle.value = "Recipes of $recipeType"

        apiServiceImpl.getMealsByCategory(
            context = context,
            category = recipeType,
            onSuccess = { meals ->
                val recipesList = meals.map { meal ->
                    RecipeListData(
                        idMeal = meal.idMeal,
                        title = meal.strMeal,
                        image = meal.strMealThumb,
                        onClick = { onRecipeClick(meal.idMeal) }
                    )
                }
                _recipes.value = recipesList
                _filteredRecipes.value = recipesList
            },
            onFail = {
                _showRetry.value = true
            },
            loadingFinished = {
                _loading.value = false
            }
        )
    }

    fun retryApiCall(categoryName: String, onRecipeClick: (String) -> Unit) {
        loadRecipesByCategory(categoryName, onRecipeClick)
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
            _filteredRecipes.value = _recipes.value
        } else {
            _filteredRecipes.value = _recipes.value.filter {
                it.title.contains(_searchQuery.value, ignoreCase = true)
            }
        }
    }

    // Actualizar los resultados de búsqueda (sugerencias)
    private fun updateSearchResults() {
        _searchResults.value = _recipes.value
            .filter { it.title.contains(_searchQuery.value, ignoreCase = true) }
            .map { it.title }
    }
}