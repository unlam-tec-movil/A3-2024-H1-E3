package ar.edu.unlam.mobile.scaffolding.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ar.edu.unlam.mobile.scaffolding.data.local.producto.dao.ProductoDao
import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.ProductoEntity
import ar.edu.unlam.mobile.scaffolding.data.local.usuario.dao.UsuarioDao
import ar.edu.unlam.mobile.scaffolding.data.local.usuario.entity.Usuario

@Database(entities = [Usuario::class, ProductoEntity::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun producotDao(): ProductoDao

    abstract fun usuarioDao(): UsuarioDao
}
