package com.example.quickStock.viewModel.home.stock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickStock.data.ProductDao
import com.example.quickStock.data.CategoryDao
import com.example.quickStock.data.QuantityExpirationDateDao
import com.example.quickStock.model.home.StockButtonData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockListViewModel @Inject constructor(
    private val productDao: ProductDao,
    private val categoryDao: CategoryDao,
    private val quantityExpirationDateDao: QuantityExpirationDateDao
) : ViewModel() {

    private val _products = MutableStateFlow<List<StockButtonData>>(emptyList())
    val products: StateFlow<List<StockButtonData>> = _products.asStateFlow()

    fun loadProductsByCategory(category: String, onProductClick: (String) -> Unit) {
        viewModelScope.launch {
            val categoryEntity = categoryDao.getCategoryByName(category)
            if (categoryEntity != null) {
                val allProducts = productDao.getAllProducts()
                val productsList = allProducts
                    .filter { it.categoryId == categoryEntity.id }
                    .map { product ->
                        val quantities = quantityExpirationDateDao.getByProductId(product.id)
                        StockButtonData(
                            title = product.name,
                            quantity = quantities.sumOf { it.quantity },
                            onClick = { onProductClick(product.id) }
                        )
                    }
                _products.value = productsList
            } else {
                _products.value = emptyList()
            }
        }
    }
}

