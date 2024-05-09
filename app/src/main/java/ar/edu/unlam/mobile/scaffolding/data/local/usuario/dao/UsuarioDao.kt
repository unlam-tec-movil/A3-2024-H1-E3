package ar.edu.unlam.mobile.scaffolding.data.local.usuario.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ar.edu.unlam.mobile.scaffolding.data.local.usuario.entity.Usuario

@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(usuario: Usuario)

    @Query("SELECT * FROM usuarios")
    fun getUsuario(): Usuario

    @Query("SELECT COUNT(*) FROM usuarios")
    fun getCantidadUsuarios(): Int
}
