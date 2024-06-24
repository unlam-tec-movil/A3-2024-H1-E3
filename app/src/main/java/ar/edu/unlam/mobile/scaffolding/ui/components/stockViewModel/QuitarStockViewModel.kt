package ar.edu.unlam.mobile.scaffolding.ui.components.stockViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.Producto
import ar.edu.unlam.mobile.scaffolding.data.repository.producto.OfflineProductoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuitarStockViewModel
    @Inject
    constructor(
        private val productoRepository: OfflineProductoRepository,
    ) : ViewModel() {
        var mapVenta by mutableStateOf<Map<String, Int>>(mapOf())

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

        var newStock by mutableIntStateOf(0)
        var scanedQr by mutableStateOf("")

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

        private suspend fun getProductoPorQR(qr: String): Producto = productoRepository.getProductoPorQR(qr)
    }
