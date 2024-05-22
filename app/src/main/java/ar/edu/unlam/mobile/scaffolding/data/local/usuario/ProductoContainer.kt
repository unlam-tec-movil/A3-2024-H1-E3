package ar.edu.unlam.mobile.scaffolding.data.local.usuario

import android.content.Context
import ar.edu.unlam.mobile.scaffolding.data.local.database.InventoryDatabase
import ar.edu.unlam.mobile.scaffolding.data.repository.producto.OfflineProductoRepository
import ar.edu.unlam.mobile.scaffolding.data.repository.producto.ProductoRepository

interface ProductoContainer {
    val productoRepository: ProductoRepository
}

class ProductoDataContainer(private val contex: Context) : ProductoContainer {
    override val productoRepository: ProductoRepository by lazy {
        OfflineProductoRepository(InventoryDatabase.getDatabase(contex).producotDao())
    }
}
