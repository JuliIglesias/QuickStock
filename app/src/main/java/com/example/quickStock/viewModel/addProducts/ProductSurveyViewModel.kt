package com.example.quickStock.viewModel.addProducts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickStock.model.addProduct.Product
import com.example.quickStock.model.addProduct.ProductFormState
import com.example.quickStock.model.addProduct.QuantityExpirationDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductSurveyViewModel : ViewModel() {

    // Estado UI para el formulario
    private val _uiState = MutableStateFlow(ProductFormState())
    val uiState: StateFlow<ProductFormState> = _uiState.asStateFlow()

    // Para manejar el estado del dropdown
    private val _isDropdownExpanded = MutableStateFlow(false)
    val isDropdownExpanded: StateFlow<Boolean> = _isDropdownExpanded.asStateFlow()

    // Funciones para actualizar el estado
    fun updateProductId(id: String) {
        _uiState.update { it.copy(productId = id) }
    }

    fun updateProductName(name: String) {
        _uiState.update { it.copy(productName = name) }
    }

    fun updateProductBrand(brand: String) {
        _uiState.update { it.copy(productBrand = brand) }
    }

    fun updateProductCategory(category: String) {
        _uiState.update { it.copy(productCategory = category) }
    }

    fun updateProductQuantity(quantity: Int) {
        _uiState.update { it.copy(productQuantity = quantity) }
    }

    fun updateExpiryDate(date: String) {
        _uiState.update { it.copy(productExpiryDate = date) }
    }

    fun incrementQuantity() {
        _uiState.update { it.copy(productQuantity = it.productQuantity + 1) }
    }

    fun decrementQuantity() {
        if (_uiState.value.productQuantity > 1) {
            _uiState.update { it.copy(productQuantity = it.productQuantity - 1) }
        }
    }

    fun toggleDropdown() {
        _isDropdownExpanded.update { !it }
    }

    fun closeDropdown() {
        _isDropdownExpanded.value = false
    }

    // private val productRepository: ProductRepository

    fun addProduct(onSuccess: (Product) -> Unit) {
        val currentState = _uiState.value

        // Agregar validaciones aquí
        if (currentState.productName.isBlank()) {
            // Manejar error
            return
        }

        val newProduct = Product(
            id = currentState.productId,
            name = currentState.productName,
            brand = currentState.productBrand,
            category = currentState.productCategory,
            quantityExpirationDate = listOf(
                QuantityExpirationDate(
                    quantity = currentState.productQuantity,
                    expiryDate = currentState.productExpiryDate
                )
            )
        )

        // Mas adelante, guardar en la base de datos
        viewModelScope.launch {
            // productRepository.saveProduct(newProduct)
            onSuccess(newProduct)
            resetForm()
        }
    }

    private fun resetForm() {
        _uiState.value = ProductFormState()
    }
}

