package ar.edu.unlam.mobile.scaffolding.domain.usecases.qr

import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.Producto
import ar.edu.unlam.mobile.scaffolding.data.repository.producto.OfflineProductoRepository

class EscanearQRUseCase(
    private val repository: OfflineProductoRepository,
) {
    suspend fun getProductoPorQR(qr: String): Producto {
        return repository.getProductoPorQR(qr)
    }
}
