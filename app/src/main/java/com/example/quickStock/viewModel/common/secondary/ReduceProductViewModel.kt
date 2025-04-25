package com.example.quickStock.viewModel.common.secondary

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ReduceProductViewModel @Inject constructor(
    // Aquí inyectarías dependencias como repositorios si los necesitas
) : ViewModel() {
    private val _selectedDateIndex = MutableStateFlow(0)
    val selectedDateIndex = _selectedDateIndex.asStateFlow()

    private val _quantityToReduce = MutableStateFlow(1)
    val quantityToReduce = _quantityToReduce.asStateFlow()

    fun updateSelectedDateIndex(index: Int, maxQuantity: Int) {
        _selectedDateIndex.value = index
        // Reseteamos la cantidad a 1 o al máximo disponible
        _quantityToReduce.value = 1.coerceAtMost(maxQuantity)
    }

    fun incrementQuantity(maxQuantity: Int) {
        if (_quantityToReduce.value < maxQuantity) {
            _quantityToReduce.value++
        }
    }

    fun decrementQuantity() {
        if (_quantityToReduce.value > 1) {
            _quantityToReduce.value--
        }
    }

    fun resetState() {
        _quantityToReduce.value = 1
        _selectedDateIndex.value = 0
    }
}