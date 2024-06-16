package ar.edu.unlam.mobile.scaffolding.ui.components.producto.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
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
    var precio by mutableDoubleStateOf(0.0)
    var stock by mutableIntStateOf(0)
    var categoria by mutableStateOf("")
    var nombreProvedor by mutableStateOf("")
    var ubicacionProveedor by mutableStateOf<LatLng?>(null)
    var qr by mutableStateOf("")
    var fotoUri by mutableStateOf("")

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
        fotoUri: String = this.fotoUri!!,
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
                    fotoUri = fotoUri,
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
        this.fotoUri = ""
    }

    var newStock by mutableIntStateOf(0)
    var scanedQr by mutableStateOf("")

    // Agregar stock
    suspend fun agregarStock(
        stock: Int = newStock,
        qr: String = scanedQr,
    ) {
        productoRepository.actualizarStock(stock, qr)
        newStock = 0
    }

    // Vender

    suspend fun vender() {
        mapVenta.map {
            quitarStock(qr = it.key, stock = it.value)
        }
    }

    private suspend fun quitarStock(
        stock: Int,
        qr: String,
    ) {
        // verificar que el stock a vender sea menor al actual
        productoRepository.restarStock(stock, qr)
    }

    var listaVenta by mutableStateOf<List<Producto>>(listOf())
    var mapVenta by mutableStateOf<Map<String, Int>>(mapOf())

    suspend fun agregarProductoVenta(
        qr: String = scanedQr,
        stock: Int = newStock,
    ) {
        val lista: MutableList<Producto> = listaVenta.toMutableList()
        val map: MutableMap<String, Int> = mapVenta.toMutableMap()
        val p = getProductoPorQR(qr)

        lista.add(p)
        listaVenta = lista.toList()

        map[qr] = stock
        mapVenta = map.toMap()
    }

    private suspend fun getProductoPorQR(qr: String): Producto {
        return productoRepository.getProductoPorQR(qr)
    }
}
