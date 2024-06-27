package ar.edu.unlam.mobile.scaffolding.data.local.producto.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.ProductoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun guardarProducto(productoEntity: ProductoEntity)

    @Query("UPDATE producto SET stock = stock + :newStock WHERE qr = :scanedQR")
    suspend fun actualizarStock(
        newStock: Int,
        scanedQR: String,
    )

    @Query("SELECT * FROM producto WHERE qr = :qr")
    suspend fun getProductoPorQR(qr: String): ProductoEntity

    @Query("UPDATE producto SET stock = stock - :newStock WHERE qr = :scanedQR")
    suspend fun restarStock(
        newStock: Int,
        scanedQR: String,
    )

    @Query("SELECT * FROM producto")
    fun getProductos(): Flow<List<ProductoEntity>>

    @Query("SELECT COUNT(*) FROM producto")
    suspend fun getCantidadProductos(): Int
}
