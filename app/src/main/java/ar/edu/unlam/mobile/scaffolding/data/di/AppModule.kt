package ar.edu.unlam.mobile.scaffolding.data.di

import android.app.Application
import androidx.room.Room
import ar.edu.unlam.mobile.scaffolding.data.local.database.InventoryDatabase
import ar.edu.unlam.mobile.scaffolding.data.local.producto.dao.ProductoDao
import ar.edu.unlam.mobile.scaffolding.data.local.usuario.dao.UsuarioDao
import ar.edu.unlam.mobile.scaffolding.data.repository.producto.OfflineProductoRepository
import ar.edu.unlam.mobile.scaffolding.data.repository.usuario.OfflineUsuarioRepository
import ar.edu.unlam.mobile.scaffolding.domain.usecases.productos.AgregarStockUseCase
import ar.edu.unlam.mobile.scaffolding.domain.usecases.productos.GuardarProductoUseCase
import ar.edu.unlam.mobile.scaffolding.domain.usecases.productos.ObtenerListaProductosUseCase
import ar.edu.unlam.mobile.scaffolding.domain.usecases.productos.VenderProductosUseCase
import ar.edu.unlam.mobile.scaffolding.domain.usecases.qr.EscanearQRUseCase
import ar.edu.unlam.mobile.scaffolding.domain.usecases.user.GuardarUsuarioUseCase
import ar.edu.unlam.mobile.scaffolding.domain.usecases.user.ObtenerUsuarioUseCase
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
    fun provideDatabase(application: Application): InventoryDatabase {
        val db =
            Room.databaseBuilder(
                application,
                InventoryDatabase::class.java,
                "app_database",
            ).build()
        return db
    }

    @Provides
    @Singleton
    fun provideProductoDao(db: InventoryDatabase): ProductoDao {
        return db.producotDao()
    }

    @Provides
    @Singleton
    fun provideSessionDao(db: InventoryDatabase): UsuarioDao {
        return db.usuarioDao()
    }

    // Usuario
    @Provides
    @Singleton
    fun provideUsuarioRepository(dao: UsuarioDao): OfflineUsuarioRepository {
        return OfflineUsuarioRepository(dao)
    }

    @Provides
    @Singleton
    fun provideGuardarUsuarioUseCase(repository: OfflineUsuarioRepository): GuardarUsuarioUseCase {
        return GuardarUsuarioUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideObtenerUsuarioUseCase(repository: OfflineUsuarioRepository): ObtenerUsuarioUseCase {
        return ObtenerUsuarioUseCase(repository)
    }

    // Productos
    @Provides
    @Singleton
    fun provideOfflineProductoRepository(productoDao: ProductoDao): OfflineProductoRepository {
        return OfflineProductoRepository(productoDao)
    }

    @Provides
    @Singleton
    fun provideGuardarProductoUseCase(repository: OfflineProductoRepository): GuardarProductoUseCase {
        return GuardarProductoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideObtenerListaProductosUseCase(repository: OfflineProductoRepository): ObtenerListaProductosUseCase {
        return ObtenerListaProductosUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAgregarStockUseCase(repository: OfflineProductoRepository): AgregarStockUseCase {
        return AgregarStockUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideVenderProductosUseCase(repository: OfflineProductoRepository): VenderProductosUseCase {
        return VenderProductosUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideEscanearQRUseCase(repository: OfflineProductoRepository): EscanearQRUseCase {
        return EscanearQRUseCase(repository)
    }
}
