package ar.edu.unlam.mobile.scaffolding.domain.usecases.productos

import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.Producto
import ar.edu.unlam.mobile.scaffolding.data.repository.producto.OfflineProductoRepository

class GuardarProductoUseCase(
    val repository: OfflineProductoRepository,
) {
    suspend fun guardarProducto(producto: Producto) {
        repository.guardarProducto(producto)
    }
}
