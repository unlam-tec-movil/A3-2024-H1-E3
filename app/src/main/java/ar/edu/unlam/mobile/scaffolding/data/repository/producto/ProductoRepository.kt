package ar.edu.unlam.mobile.scaffolding.data.repository.producto

import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.Producto

interface ProductoRepository {
    suspend fun getCantidadProducto(): Int

    fun getProducto(): List<Producto>

    suspend fun guardarProducto(
        nombre: String,
        precio: Double,
        stock: Int,
        categoria: String,
        nombreProvedor: String,
        qr: String,
    )
}
