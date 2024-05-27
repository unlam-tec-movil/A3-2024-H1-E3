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
    fun guardarProducto(productoEntity: ProductoEntity)

    @Query("SELECT * FROM producto")
    fun getProductos(): Flow<List<ProductoEntity>>

    @Query("SELECT COUNT(*) FROM producto")
    suspend fun getCantidadProductos(): Int
}
