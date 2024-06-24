package ar.edu.unlam.mobile.scaffolding.ui.components.stockViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.data.repository.producto.OfflineProductoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AgregarStockViewModel
    @Inject
    constructor(
        private val productoRepository: OfflineProductoRepository,
    ) : ViewModel() {
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
    }
