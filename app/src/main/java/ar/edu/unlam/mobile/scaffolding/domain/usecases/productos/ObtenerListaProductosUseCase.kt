package ar.edu.unlam.mobile.scaffolding.domain.usecases.productos

import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.Producto
import ar.edu.unlam.mobile.scaffolding.data.repository.producto.OfflineProductoRepository
import kotlinx.coroutines.flow.Flow

class ObtenerListaProductosUseCase(
    val repository: OfflineProductoRepository,
) {
    fun obtenerListaProductos(): Flow<List<Producto>> {
        return repository.getProductos()
    }
}
