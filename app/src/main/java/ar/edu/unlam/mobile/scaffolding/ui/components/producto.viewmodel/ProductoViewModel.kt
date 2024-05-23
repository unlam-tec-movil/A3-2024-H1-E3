package ar.edu.unlam.mobile.scaffolding.ui.components.usuario.viewmodel

import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.data.repository.producto.ProductoRepository

class ProductoViewModel(private val productoRepository: ProductoRepository) : ViewModel() {
    suspend fun getCantidadProducto(): Int {
        return productoRepository.getCantidadProducto()
    }

    suspend fun guardarProducto(
        nombre: String,
        negocio: String,
    ) {
        productoRepository.guardarProducto(nombre = nombre, negocio = negocio)
    }
}
