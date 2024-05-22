package ar.edu.unlam.mobile.scaffolding.data.repository.producto

import ar.edu.unlam.mobile.scaffolding.data.local.usuario.entity.Producto

interface ProductoRepository {
    suspend fun getCantidadProducto(): Int

    fun getProducto(): Producto

    suspend fun guardarProducto(
        nombre: String,
        negocio: String,
    )
}
