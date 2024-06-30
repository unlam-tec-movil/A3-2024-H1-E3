package ar.edu.unlam.mobile.scaffolding.domain.usecases.productos

import ar.edu.unlam.mobile.scaffolding.data.repository.producto.OfflineProductoRepository

class VenderProductosUseCase(
    private val repository: OfflineProductoRepository,
) {
    suspend fun restarStock(
        stock: Int,
        qr: String,
    ) {
        repository.restarStock(stock, qr)
    }
}
