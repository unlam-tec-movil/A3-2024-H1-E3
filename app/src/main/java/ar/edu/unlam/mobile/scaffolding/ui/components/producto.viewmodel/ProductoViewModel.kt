package ar.edu.unlam.mobile.scaffolding.ui.components.usuario.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.Producto
import ar.edu.unlam.mobile.scaffolding.data.repository.producto.OfflineProductoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductoViewModel(private val productoRepository: OfflineProductoRepository) : ViewModel() {
    val productos: StateFlow<List<Producto>> = productoRepository.getProductos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList(),
        )

    fun guardarProducto(
        nombre: String,
        precio: Double,
        stock: Int,
        categoria: String,
        nombreProvedor: String,
        qr: String,
    ) { viewModelScope.launch(Dispatchers.IO) {
        try {
            productoRepository.guardarProducto(nombre = nombre, precio = precio, stock = stock, categoria = categoria, nombreProvedor = nombreProvedor, qr = qr)
        } catch (e: Exception) {
            System.out.println("NO ANDA")
        }
    }
    }
}
