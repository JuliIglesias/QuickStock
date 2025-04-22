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
                _recipes.value = meals.map { meal ->
                    RecipeListData(
                        idMeal = meal.idMeal,
                        title = meal.strMeal,
                        image = meal.strMealThumb,
                        onClick = { onRecipeClick(meal.idMeal) }
                    )
                }
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
}