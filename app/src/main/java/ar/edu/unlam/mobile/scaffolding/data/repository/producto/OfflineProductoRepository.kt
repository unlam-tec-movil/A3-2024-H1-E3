package ar.edu.unlam.mobile.scaffolding.data.repository.producto

import ar.edu.unlam.mobile.scaffolding.data.local.usuario.dao.ProductoDao
import ar.edu.unlam.mobile.scaffolding.data.local.usuario.entity.Producto

class OfflineProductoRepository(private val productoDao: ProductoDao) : ProductoRepository {
    override suspend fun guardarProducto(
        nombre: String,
        negocio: String,
    ) {
        val u =
            Producto(
                nombre = nombre,
                negocio = negocio,
            )
        productoDao.guardarProducto(u)
    }

    override suspend fun getCantidadProducto(): Int = productoDao.getCantidadProductos()

    override fun getProducto(): Producto {
        return productoDao.getProducto().first()
    }
}
