package com.example.quickStock.viewModel.home.stock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickStock.mocking.getProductsByCategory
import com.example.quickStock.model.home.StockButtonData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockListViewModel @Inject constructor(
) : ViewModel() {

    private val _products = MutableStateFlow<List<StockButtonData>>(emptyList())
    val products: StateFlow<List<StockButtonData>> = _products.asStateFlow()

    fun loadProductsByCategory(category: String, onProductClick: (String) -> Unit) {
        viewModelScope.launch {
            val productsList = getProductsByCategory(category).map { product ->
                StockButtonData(
                    title = product.name,
                    quantity = product.quantityExpirationDate.sumOf { it.quantity },
                    onClick = { onProductClick(product.id) }
                )
            }
            _products.value = productsList
        }
    }
}