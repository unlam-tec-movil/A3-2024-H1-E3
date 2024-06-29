package ar.edu.unlam.mobile.scaffolding

import ar.edu.unlam.mobile.scaffolding.data.local.producto.dao.ProductoDao
import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.Producto
import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.ProductoEntity
import ar.edu.unlam.mobile.scaffolding.data.repository.producto.OfflineProductoRepository
import ar.edu.unlam.mobile.scaffolding.domain.usecases.productos.AgregarStockUseCase
import ar.edu.unlam.mobile.scaffolding.domain.usecases.productos.GuardarProductoUseCase
import ar.edu.unlam.mobile.scaffolding.domain.usecases.productos.VenderProductosUseCase
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class ProductoRepositoryTest {
    @Mock
    private lateinit var mockedDao: ProductoDao

    @InjectMocks
    private lateinit var repository: OfflineProductoRepository

    @Before
    fun setup() {
        mockedDao = mock(ProductoDao::class.java)
        repository = OfflineProductoRepository(mockedDao)
    }

    @Test
    fun queSePuedaGuardarUnProducto() {
        runTest {
            val useCase = GuardarProductoUseCase(repository)

            val producto =
                Producto(
                    nombre = "test",
                    precio = 100.0,
                    stock = 50,
                    categoria = "test",
                    nombreProvedor = "test",
                    ubicacionProveedor = LatLng(0.0, 0.0),
                    qr = "test01",
                    fotoUri = "",
                )

            // Verifica que no exista ningún producto
            `when`(mockedDao.getCantidadProductos()).thenReturn(0)
            var valorObtenido = mockedDao.getCantidadProductos()
            assertEquals(0, valorObtenido)

            useCase.guardarProducto(producto)

            `when`(mockedDao.getCantidadProductos()).thenReturn(1)
            valorObtenido = mockedDao.getCantidadProductos()
            assertEquals(1, valorObtenido)
        }
    }

    @Test
    fun queSePuedaObtenerUnProductoPorSuCodigoQR() {
        runTest {
            val givenProducto =
                ProductoEntity(
                    nombre = "test",
                    precio = 100.0,
                    stock = 50,
                    categoria = "test",
                    nombreProvedor = "test",
                    latitudProveedor = 0.0,
                    longitudProveedor = 0.0,
                    qr = "test01",
                    fotoUri = "",
                )
            `when`(mockedDao.getProductoPorQR("test01")).thenReturn(givenProducto)
            val expected = mockedDao.getProductoPorQR("test01")

            assertEquals(expected, givenProducto)
        }
    }

    @Test
    fun queSePuedaAgregarStockAUnProducto() {
        runTest {
            val agregarStockUseCase = AgregarStockUseCase(repository)

            var producto =
                ProductoEntity(
                    nombre = "test",
                    precio = 100.0,
                    stock = 50,
                    categoria = "test",
                    nombreProvedor = "test",
                    latitudProveedor = 0.0,
                    longitudProveedor = 0.0,
                    qr = "test01",
                    fotoUri = "",
                )
            mockedDao.guardarProducto(producto)
            `when`(mockedDao.getProductoPorQR("test01")).thenReturn(producto)
            var valorObtenido = mockedDao.getProductoPorQR("test01").stock
            assertEquals(50, valorObtenido)

            agregarStockUseCase.agregarStock(10, "test01")

            producto =
                ProductoEntity(
                    nombre = "test",
                    precio = 100.0,
                    stock = 60,
                    categoria = "test",
                    nombreProvedor = "test",
                    latitudProveedor = 0.0,
                    longitudProveedor = 0.0,
                    qr = "test01",
                    fotoUri = "",
                )
            `when`(mockedDao.getProductoPorQR("test01")).thenReturn(producto)
            valorObtenido = mockedDao.getProductoPorQR("test01").stock
            assertEquals(60, valorObtenido)
        }
    }

    @Test
    fun queSePuedaVenderCiertaCantidadDeUnProducto() {
        runTest {
            val useCase = VenderProductosUseCase(repository)

            var producto =
                ProductoEntity(
                    nombre = "test",
                    precio = 100.0,
                    stock = 50,
                    categoria = "test",
                    nombreProvedor = "test",
                    latitudProveedor = 0.0,
                    longitudProveedor = 0.0,
                    qr = "test01",
                    fotoUri = "",
                )
            mockedDao.guardarProducto(producto)
            `when`(mockedDao.getProductoPorQR("test01")).thenReturn(producto)
            var valorObtenido = mockedDao.getProductoPorQR("test01").stock
            assertEquals(50, valorObtenido)

            useCase.restarStock(10, "test01")

            producto =
                ProductoEntity(
                    nombre = "test",
                    precio = 100.0,
                    stock = 40,
                    categoria = "test",
                    nombreProvedor = "test",
                    latitudProveedor = 0.0,
                    longitudProveedor = 0.0,
                    qr = "test01",
                    fotoUri = "",
                )
            `when`(mockedDao.getProductoPorQR("test01")).thenReturn(producto)
            valorObtenido = mockedDao.getProductoPorQR("test01").stock
            assertEquals(40, valorObtenido)
        }
    }
}
