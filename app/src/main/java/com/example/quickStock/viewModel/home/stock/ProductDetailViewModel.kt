package com.example.quickStock.viewModel.home.stock

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quickStock.R
import com.example.quickStock.data.ProductDao
import com.example.quickStock.data.QuantityExpirationDateDao
import com.example.quickStock.data.CategoryDao
import com.example.quickStock.model.addProduct.Product
import com.example.quickStock.model.addProduct.QuantityExpirationDate
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val productDao: ProductDao,
    private val quantityExpirationDateDao: QuantityExpirationDateDao,
    private val categoryDao: CategoryDao
) : ViewModel() {

    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product.asStateFlow()

    fun setProduct(product: Product) {
        viewModelScope.launch {
            val productEntity = productDao.getAllProducts().find { it.id == product.id }
            if (productEntity != null) {
                val category = categoryDao.getCategoryById(productEntity.categoryId)?.name ?: context.getString(
                    R.string.nothing_String
                )
                val quantities = quantityExpirationDateDao.getByProductId(product.id)
                _product.value = Product(
                    id = productEntity.id,
                    name = productEntity.name,
                    brand = productEntity.brand,
                    category = category,
                    quantityExpirationDate = quantities.map { QuantityExpirationDate(it.quantity, it.expiryDate) }
                )
            } else {
                _product.value = product
            }
        }
    }

    suspend fun getProductById(productId: String): Product? {
        val productEntity = productDao.getAllProducts().find { it.id == productId }
        return if (productEntity != null) {
            val category = categoryDao.getCategoryById(productEntity.categoryId)?.name ?: context.getString(
                R.string.nothing_String
            )
            val quantities = quantityExpirationDateDao.getByProductId(productId)
            Product(
                id = productEntity.id,
                name = productEntity.name,
                brand = productEntity.brand,
                category = category,
                quantityExpirationDate = quantities.map { QuantityExpirationDate(it.quantity, it.expiryDate) }
            )
        } else {
            null
        }
    }

    fun setProductById(productId: String) {
        viewModelScope.launch {
            _product.value = getProductById(productId)
        }
    }

    fun reduceProductQuantity(expiryDate: String, quantity: Int) {
        val currentProduct = _product.value ?: return
        viewModelScope.launch {
            val quantities = quantityExpirationDateDao.getByProductId(currentProduct.id) ?: emptyList()
            val updatedQuantities = quantities.mapNotNull { item ->
                if (item.expiryDate == expiryDate) {
                    val newQuantity = (item.quantity - quantity).coerceAtLeast(0)
                    if (newQuantity > 0) {
                        item.copy(quantity = newQuantity)
                    } else {
                        null
                    }
                } else {
                    item
                }
            }
            for (item in quantities) {
                if (item.expiryDate == expiryDate) {
                    val newQuantity = (item.quantity - quantity).coerceAtLeast(0)
                    if (newQuantity > 0) {
                        quantityExpirationDateDao.update(item.copy(quantity = newQuantity))
                    } else {
                        quantityExpirationDateDao.delete(item)
                    }
                }
            }
            setProduct(currentProduct)
        }
    }
}

