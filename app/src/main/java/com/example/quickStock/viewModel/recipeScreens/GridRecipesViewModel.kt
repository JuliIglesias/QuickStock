package com.example.quickStock.viewModel.recipeScreens

import androidx.lifecycle.ViewModel
import com.example.quickStock.apiManager.ApiServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import android.content.Context
import com.example.quickStock.model.recipe.RecipeButtonIdData
import com.example.quickStock.screensUI.icon.getRecipesTypesIconId
import dagger.hilt.android.qualifiers.ApplicationContext

@HiltViewModel
class GridRecipesViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val apiServiceImpl: ApiServiceImpl,
): ViewModel() {

    // Estado para la búsqueda
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Lista completa de recetas
    private val _allRecipes = MutableStateFlow<List<RecipeButtonIdData>>(emptyList())

    // Lista filtrada de recetas (lo que se muestra)
    private val _filteredRecipes = MutableStateFlow<List<RecipeButtonIdData>>(emptyList())
    val filteredRecipes: StateFlow<List<RecipeButtonIdData>> = _filteredRecipes.asStateFlow()

    // Lista de resultados de búsqueda (solo títulos)
    private val _searchResults = MutableStateFlow<List<String>>(emptyList())
    val searchResults: StateFlow<List<String>> = _searchResults.asStateFlow()

    // Inicializar el ViewModel con la lista de recetas
    private var recipesInitialized = false

    // Loading state
    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    // Retry state
    private val _showRetry = MutableStateFlow(false)
    val showRetry = _showRetry.asStateFlow()

    // Para guardar el callback de navegación
    private var onRecipeClickListener: ((String, String) -> Unit)? = null

    //------ API call state management -------

    init {
        loadRecipeCategories()
    }

    fun retryApiCall() {
        loadRecipeCategories()
    }

    // Ahora pasamos tanto el ID como el nombre de la categoría en la navegación
    fun setOnRecipeClickListener(listener: (String, String) -> Unit) {
        onRecipeClickListener = listener
    }

    private fun loadRecipeCategories() {
        _loading.value = true
        _showRetry.value = false

        apiServiceImpl.getRecipeTypes(
            context = context,
            onSuccess = { recipeTypes ->
                // Convertir RecipeType a RecipeButtonIdData
                val buttonDataIdList = recipeTypes.map { recipeType ->
                    RecipeButtonIdData(
                        title = recipeType.strCategory,
                        icon = getRecipesTypesIconId(context, recipeType.strCategory),
                        onClick = {
                            // Usamos el ID y el nombre de la categoría para la navegación
                            onRecipeClickListener?.invoke(
                                recipeType.idCategory.toString(),
                                recipeType.strCategory
                            )
                        }
                    )
                }

                _allRecipes.value = buttonDataIdList
                _filteredRecipes.value = buttonDataIdList
                updateSearchResults()
            },
            onFail = {
                _showRetry.value = true
            },
            loadingFinished = {
                _loading.value = false
            }
        )
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