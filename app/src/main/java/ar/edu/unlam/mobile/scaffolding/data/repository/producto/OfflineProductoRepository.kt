package ar.edu.unlam.mobile.scaffolding.data.repository.producto

import ar.edu.unlam.mobile.scaffolding.data.local.producto.dao.ProductoDao
import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.Producto
import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.ProductoEntity
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineProductoRepository(private val productoDao: ProductoDao) {
    fun guardarProducto(
        nombre: String,
        precio: Double,
        stock: Int,
        categoria: String,
        nombreProvedor: String,
        ubicacionProveedor: LatLng,
        qr: String,
    ) {
        val u =
            ProductoEntity(
                nombre = nombre,
                precio = precio,
                stock = stock,
                categoria = categoria,
                nombreProvedor = nombreProvedor,
                latitudProveedor = ubicacionProveedor.latitude,
                longitudProveedor = ubicacionProveedor.longitude,
                qr = qr,
            )
        productoDao.guardarProducto(u)
    }

    suspend fun getCantidadProducto(): Int = productoDao.getCantidadProductos()

    fun getProductos(): Flow<List<Producto>> {
        return productoDao.getProductos().map { entities ->
            entities.map { entity ->
                Producto(
                    nombre = entity.nombre,
                    precio = entity.precio,
                    stock = entity.stock,
                    categoria = entity.categoria,
                    nombreProvedor = entity.nombreProvedor,
                    ubicacionProveedor = LatLng(entity.latitudProveedor, entity.longitudProveedor),
                    qr = entity.qr,
                )
            }
        }
    }
}
