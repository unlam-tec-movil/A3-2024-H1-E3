package ar.edu.unlam.mobile.scaffolding.data.repository.producto

import ar.edu.unlam.mobile.scaffolding.data.local.producto.dao.ProductoDao
import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.Producto

class OfflineProductoRepository(private val productoDao: ProductoDao) : ProductoRepository {
    override suspend fun guardarProducto(
        nombre: String,
        precio: Double,
        stock: Int,
        categoria: String,
        nombreProvedor: String,
        qr: String
    ) {
        val u =
            Producto(
                nombre = nombre,
                precio = precio,
                stock = stock,
                categoria = categoria,
                nombreProvedor = nombreProvedor,
                qr = qr
            )
        productoDao.guardarProducto(u)
    }

    override suspend fun getCantidadProducto(): Int = productoDao.getCantidadProductos()

    override fun getProducto(): Producto {
        return productoDao.getProducto().first()
    }
}
