package com.example.quickStock.viewModel.addProducts

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickStock.R
import com.example.quickStock.data.CategoryDao
import com.example.quickStock.data.ProductDao
import com.example.quickStock.data.QuantityExpirationDateDao
import com.example.quickStock.data.Product as ProductEntity
import com.example.quickStock.data.QuantityExpirationDate as QuantityExpirationDateEntity
import com.example.quickStock.data.Category
import com.example.quickStock.model.addProduct.Product
import com.example.quickStock.model.addProduct.ProductFormState
import com.example.quickStock.model.addProduct.QuantityExpirationDate
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductSurveyViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val productDao: ProductDao,
    private val categoryDao: CategoryDao,
    private val quantityExpirationDateDao: QuantityExpirationDateDao
) : ViewModel() {

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

    fun addProduct(onSuccess: (Product) -> Unit) {
        val currentState = _uiState.value

        if (currentState.productName.isBlank()) {
            // Manejar error
            return
        }

        viewModelScope.launch {
            // Buscar o crear la categoría
            var category = categoryDao.getCategoryByName(currentState.productCategory)
            if (category == null) {
                val categoryId = categoryDao.insert(Category(name = currentState.productCategory))
                category = Category(id = categoryId, name = currentState.productCategory)
            }

            // Verificar si el producto ya existe
            val existingProduct = productDao.getProductById(currentState.productId)
            if (existingProduct == null) {
                // Crear y guardar el producto si no existe
                val productEntity = ProductEntity(
                    id = currentState.productId,
                    name = currentState.productName,
                    brand = currentState.productBrand,
                    categoryId = category.id
                )
                productDao.insert(productEntity)
            }

            // Guardar la cantidad y fecha de vencimiento (si ya existe, solo agrega otro registro)
            val quantityEntity = QuantityExpirationDateEntity(
                productId = currentState.productId,
                quantity = currentState.productQuantity,
                expiryDate = currentState.productExpiryDate
            )
            quantityExpirationDateDao.insert(quantityEntity)

            // Devolver el producto en el modelo de UI
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
            onSuccess(newProduct)
            resetForm()
        }
    }

    fun fillProductFieldsFromBarcode(barcode: String) {
        viewModelScope.launch {
            val product = productDao.getProductById(barcode)
            if (product != null) {
                val category = categoryDao.getCategoryById(product.categoryId)
                val quantityExp = quantityExpirationDateDao.getByProductId(barcode).firstOrNull()
                _uiState.update {
                    it.copy(
                        productId = product.id,
                        productName = product.name,
                        productBrand = product.brand,
                        productCategory = category?.name ?: context.getString(
                            R.string.nothing_String
                        ),
                        productQuantity = quantityExp?.quantity ?: 1,
                        productExpiryDate = quantityExp?.expiryDate ?: context.getString(
                            R.string.nothing_String
                        )
                    )
                }
            }
        }
    }

    private fun resetForm() {
        _uiState.value = ProductFormState()
    }
}

