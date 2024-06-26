package ar.edu.unlam.mobile.scaffolding.data.di

import android.app.Application
import androidx.room.Room
import ar.edu.unlam.mobile.scaffolding.data.local.database.InventoryDatabase
import ar.edu.unlam.mobile.scaffolding.data.local.producto.dao.ProductoDao
import ar.edu.unlam.mobile.scaffolding.data.repository.producto.OfflineProductoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): InventoryDatabase =
        Room.databaseBuilder(app, InventoryDatabase::class.java, "producto_db").build()

    @Provides
    @Singleton
    fun provideProductoDao(db: InventoryDatabase): ProductoDao = db.producotDao()

    @Provides
    @Singleton
    fun provideProductoRepository(dao: ProductoDao): OfflineProductoRepository = OfflineProductoRepository(dao)
}
