package ar.edu.unlam.mobile.scaffolding.domain.usecases.productos

import ar.edu.unlam.mobile.scaffolding.data.repository.producto.OfflineProductoRepository

class AgregarStockUseCase(
    val repository: OfflineProductoRepository,
) {
    suspend fun agregarStock(
        stock: Int,
        qr: String,
    ) {
        repository.actualizarStock(stock, qr)
    }
}
