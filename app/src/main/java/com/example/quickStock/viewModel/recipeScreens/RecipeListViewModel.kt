package com.example.quickStock.viewModel.recipeScreens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickStock.mocking.getRecipesByType
import com.example.quickStock.model.recipe.RecipeListData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeListViewModel : ViewModel() {

    // Estado para el título de la pantalla
    private val _screenTitle = MutableStateFlow("")
    val screenTitle: StateFlow<String> = _screenTitle.asStateFlow()

    // Estado para la lista de recetas
    private val _recipes = MutableStateFlow<List<RecipeListData>>(emptyList())
    val recipes: StateFlow<List<RecipeListData>> = _recipes.asStateFlow()

    // Cargar recetas según el tipo
    fun loadRecipesByType(recipeType: String, onRecipeClick: (String) -> Unit) {
        viewModelScope.launch {

                _screenTitle.value = "Recipes of $recipeType"

                val recipesList = getRecipesByType(recipeType)

                _recipes.value = recipesList.map { recipe ->
                    RecipeListData(
                        title = recipe.name,
                        image = recipe.image,
                        onClick = { onRecipeClick(recipe.id) } // Navegación
                    )
                }
        }
    }

}