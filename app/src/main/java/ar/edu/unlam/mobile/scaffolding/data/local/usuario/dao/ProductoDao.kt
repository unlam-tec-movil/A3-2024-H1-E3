package ar.edu.unlam.mobile.scaffolding.data.local.usuario.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ar.edu.unlam.mobile.scaffolding.data.local.usuario.entity.Producto

@Dao
interface ProductoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun guardarProducto(producto: Producto)

    @Query("SELECT * FROM producto")
    fun getProducto(): List<Producto>

    @Query("SELECT COUNT(*) FROM producto")
    suspend fun getCantidadProductos(): Int
}
