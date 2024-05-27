package ar.edu.unlam.mobile.scaffolding.data.repository.producto

import ar.edu.unlam.mobile.scaffolding.data.local.producto.dao.ProductoDao
import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.Producto
import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.ProductoEntity

class OfflineProductoRepository(private val productoDao: ProductoDao) {
    fun guardarProducto(
        nombre: String,
        precio: Double,
        stock: Int,
        categoria: String,
        nombreProvedor: String,
        qr: String,
    ) {
        val u =
            ProductoEntity(
                nombre = nombre,
                precio = precio,
                stock = stock,
                categoria = categoria,
                nombreProvedor = nombreProvedor,
                qr = qr,
            )
        productoDao.guardarProducto(u)
    }

    suspend fun getCantidadProducto(): Int = productoDao.getCantidadProductos()

    fun getProducto(): List<Producto> {
        val entities = productoDao.getProducto()
        return entities.map {
            Producto(
                nombre = it.nombre,
                precio = it.precio,
                stock = it.stock,
                categoria = it.categoria,
                nombreProvedor = it.nombreProvedor,
                qr = it.qr,
            )
        }
    }
}
