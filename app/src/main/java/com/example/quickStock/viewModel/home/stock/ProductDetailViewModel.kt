package com.example.quickStock.viewModel.home.stock

import androidx.lifecycle.ViewModel
import com.example.quickStock.model.addProduct.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    // Aquí inyecta el repositorio si tienes uno
    // private val productRepository: ProductRepository
) : ViewModel() {

    private val _product = MutableStateFlow<Product?>(null)
    val product = _product.asStateFlow()

    fun setProduct(product: Product) {
        _product.value = product
    }

    fun reduceProductQuantity(expiryDate: String, quantity: Int) {
        val currentProduct = _product.value ?: return

        // Buscar y actualizar la cantidad del producto con la fecha de caducidad especificada
        val updatedQuantityList = currentProduct.quantityExpirationDate.map { item ->
            if (item.expiryDate == expiryDate) {
                // Asegúrate de no poner una cantidad negativa
                val newQuantity = (item.quantity - quantity).coerceAtLeast(0)
                if (newQuantity > 0) {
                    item.copy(quantity = newQuantity)
                } else {
                    null // Si la cantidad llega a 0, eliminaremos este item
                }
            } else {
                item
            }
        }.filterNotNull() // Eliminar los items con cantidad 0

        // Actualizar el producto con la nueva lista de cantidades
        _product.value = currentProduct.copy(
            quantityExpirationDate = updatedQuantityList
        )

    }
}