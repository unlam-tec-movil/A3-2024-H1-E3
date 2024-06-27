package ar.edu.unlam.mobile.scaffolding.ui.components.viewmodels.stock

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.Producto
import ar.edu.unlam.mobile.scaffolding.domain.usecases.productos.VenderProductosUseCase
import ar.edu.unlam.mobile.scaffolding.domain.usecases.qr.EscanearQRUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class VenderProductosViewModel
    @Inject
    constructor(
        private val venderProductosUseCase: VenderProductosUseCase,
        private val escanearQRUseCase: EscanearQRUseCase,
    ) : ViewModel() {
        var stock by mutableIntStateOf(0)
        var qr by mutableStateOf("")

        private val _listaVenta = MutableStateFlow<List<Producto>>(emptyList())
        val listaVenta: StateFlow<List<Producto>> get() = _listaVenta
        var mapVenta by mutableStateOf<Map<String, Int>>(mapOf())

        suspend fun vender() {
            mapVenta.map {
                restarStock()
            }
            limpiarLista()
        }

        suspend fun agregarProductoLista() {
            val lista: MutableList<Producto> = listaVenta.value.toMutableList()
            val map: MutableMap<String, Int> = mapVenta.toMutableMap()
            val p = escanearQR()

            lista.add(p)
            _listaVenta.value = lista.toList()

            map[qr] = stock
            mapVenta = map.toMap()

            reestablecerDatos()
        }

        private fun reestablecerDatos() {
            stock = 0
            qr = ""
        }

        private fun limpiarLista() {
            _listaVenta.value = listOf()
            mapVenta = mapOf()
        }

        suspend fun restarStock() {
            venderProductosUseCase.restarStock(
                stock,
                qr,
            )
        }

        suspend fun escanearQR(): Producto {
            return escanearQRUseCase.getProductoPorQR(qr)
        }
    }
