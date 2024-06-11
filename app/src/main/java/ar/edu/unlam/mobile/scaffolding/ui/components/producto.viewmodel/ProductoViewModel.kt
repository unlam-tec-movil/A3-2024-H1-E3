package ar.edu.unlam.mobile.scaffolding.ui.components.usuario.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.Producto
import ar.edu.unlam.mobile.scaffolding.data.repository.producto.OfflineProductoRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductoViewModel(private val productoRepository: OfflineProductoRepository) : ViewModel() {
    var nombre by mutableStateOf("")
    var textP by mutableStateOf("")
    var textS by mutableStateOf("")
    var precio by mutableStateOf(0.0)
    var stock by mutableStateOf(0)
    var categoria by mutableStateOf("")
    var nombreProvedor by mutableStateOf("")
    var ubicacionProveedor by mutableStateOf<LatLng?>(null)
    var qr by mutableStateOf("")
    var detalle: Producto? = null

    val productos: StateFlow<List<Producto>> =
        productoRepository.getProductos()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList(),
            )

    fun productoDetalle(): Producto? {
        return detalle
    }

    fun productoDetalleGuardar(producto: Producto) {
        detalle = producto
    }

    fun guardarProducto(
        nombre: String = this.nombre,
        precio: Double = this.precio,
        stock: Int = this.stock,
        categoria: String = this.categoria,
        nombreProvedor: String = this.nombreProvedor,
        ubicacionProveedor: LatLng = this.ubicacionProveedor!!,
        qr: String = this.qr,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                productoRepository.guardarProducto(
                    nombre = nombre,
                    precio = precio,
                    stock = stock,
                    categoria = categoria,
                    nombreProvedor = nombreProvedor,
                    ubicacionProveedor = ubicacionProveedor,
                    qr = qr,
                )
            } catch (e: Exception) {
                System.out.println("NO ANDA")
            }
        }
        this.nombre = ""
        this.precio = 0.0
        this.stock = 0
        this.categoria = ""
        this.nombreProvedor = ""
        this.ubicacionProveedor = null
        this.qr = ""
    }
}
