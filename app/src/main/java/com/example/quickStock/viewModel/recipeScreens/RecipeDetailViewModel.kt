package com.example.quickStock.viewModel.recipeScreens

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.quickStock.apiManager.ApiServiceImpl
import com.example.quickStock.apiManager.responses.MealDetail
import com.example.quickStock.model.recipe.RecipeData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val apiServiceImpl: ApiServiceImpl
) : ViewModel() {

    // Estado para la receta
    private val _recipe = MutableStateFlow<RecipeData?>(null)
    val recipe: StateFlow<RecipeData?> = _recipe.asStateFlow()

    // Estado de carga
    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    // Estado para mostrar la opción de reintentar
    private val _showRetry = MutableStateFlow(false)
    val showRetry: StateFlow<Boolean> = _showRetry.asStateFlow()

    // Manejar el ID actual para reintentos
    private var currentMealId: String = ""

    // Cargar detalles de la receta
    fun loadRecipeDetails(mealId: String) {
        _loading.value = true
        _showRetry.value = false
        currentMealId = mealId

        apiServiceImpl.getMealById(
            context = context,
            mealId = mealId,
            onSuccess = { mealDetail ->
                if (mealDetail != null) {
                    _recipe.value = convertMealDetailToRecipeData(mealDetail)
                }
                _loading.value = false
            },
            onFail = {
                _loading.value = false
                _showRetry.value = true
            },
            loadingFinished = {
                // Este callback ya no es necesario ya que manejamos el estado en los otros callbacks
            }
        )
    }

    // Reintentar la carga
    fun retryLoadingRecipe() {
        if (currentMealId.isNotEmpty()) {
            loadRecipeDetails(currentMealId)
        }
    }

    // Función para convertir MealDetail a RecipeData
    private fun convertMealDetailToRecipeData(mealDetail: MealDetail): RecipeData {
        val ingredientsWithMeasures = mealDetail.getIngredientsListWithMeasures()

        return RecipeData(
            id = mealDetail.idMeal,
            name = mealDetail.strMeal,
            image = mealDetail.strMealThumb,
            // Limpiamos las instrucciones y quitamos la numeración
            steps = cleanInstructions(mealDetail.strInstructions),
            ingredients = ingredientsWithMeasures.map { it.first },
            measurements = ingredientsWithMeasures.map { it.second },
            youtubeUrl = mealDetail.strYoutube
        )
    }

    // Función para limpiar las instrucciones
    private fun cleanInstructions(instructions: String): List<String> {
        // Dividimos por saltos de línea
        val rawLines = instructions.split("\r\n", "\n")

        // Patrón para identificar líneas que son solo números
        val numberOnlyPattern = Regex("^\\s*\\d+\\s*$")

        // Patrón para remover números al inicio de un paso (ej: "1. " o "2) " o "3 - ")
        val leadingNumberPattern = Regex("^\\s*\\d+[.\\)\\s\\-–—]*\\s*")

        // Filtramos líneas vacías y las que solo contienen números
        val cleanSteps = rawLines
            .filter { !it.isBlank() && !numberOnlyPattern.matches(it) }
            .map { line ->
                // Eliminamos la numeración al inicio de cada paso
                line.replace(leadingNumberPattern, "").trim()
            }
            .filter { it.isNotBlank() }

        return cleanSteps
    }
}