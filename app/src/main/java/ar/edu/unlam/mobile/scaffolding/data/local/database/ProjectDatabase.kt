package ar.edu.unlam.mobile.scaffolding.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ar.edu.unlam.mobile.scaffolding.data.local.usuario.dao.ProductoDao
import ar.edu.unlam.mobile.scaffolding.data.local.usuario.entity.Producto

@Database(entities = [Producto::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun producotDao(): ProductoDao

    companion object {
        @Volatile
        private var instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .build()
                    .also { instance = it }
            }
        }
    }
}
