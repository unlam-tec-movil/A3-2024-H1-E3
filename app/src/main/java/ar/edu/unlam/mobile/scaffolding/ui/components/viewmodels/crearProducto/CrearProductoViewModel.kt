package ar.edu.unlam.mobile.scaffolding.ui.components.viewmodels.crearProducto

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.Producto
import ar.edu.unlam.mobile.scaffolding.domain.usecases.productos.GuardarProductoUseCase
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CrearProductoViewModel
    @Inject
    constructor(
        private val guardarProductoUseCase: GuardarProductoUseCase,
    ) : ViewModel() {
        var nombre by mutableStateOf("")
        var precio by mutableDoubleStateOf(0.0)
        var stock by mutableIntStateOf(0)
        var categoria by mutableStateOf("")
        var nombreProvedor by mutableStateOf("")
        var ubicacionProveedor by mutableStateOf(LatLng(0.0, 0.0))
        var qr by mutableStateOf("")
        var fotoUri by mutableStateOf("")

        suspend fun guardarProducto() {
            val producto =
                Producto(
                    nombre = nombre,
                    precio = precio,
                    stock = stock,
                    categoria = categoria,
                    nombreProvedor = nombreProvedor,
                    ubicacionProveedor = ubicacionProveedor,
                    qr = qr,
                    fotoUri = fotoUri,
                )
            guardarProductoUseCase.guardarProducto(producto)
            reestablecerValores()
        }

        fun reestablecerValores() {
            nombre = ""
            precio = 0.0
            stock = 0
            categoria = ""
            nombreProvedor = ""
            ubicacionProveedor = LatLng(0.0, 0.0)
            qr = ""
            fotoUri = ""
        }
    }
