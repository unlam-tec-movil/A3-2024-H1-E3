package ar.edu.unlam.mobile.scaffolding
import android.content.Context
import ar.edu.unlam.mobile.scaffolding.data.local.producto.dao.ProductoDao
import ar.edu.unlam.mobile.scaffolding.data.repository.producto.OfflineProductoRepository
import ar.edu.unlam.mobile.scaffolding.domain.usecases.productos.GuardarProductoUseCase
import ar.edu.unlam.mobile.scaffolding.ui.components.viewmodels.crearProducto.CrearProductoViewModel
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductoViewModelTest {
    private val dao = mock(ProductoDao::class.java)
    private val repo = OfflineProductoRepository(dao)
    private val useCase = GuardarProductoUseCase(repo)

    // Configuración inicial

    @Test
    fun `testGuardarProductoExitosamente`() =
        runBlocking {
            val viewModel = CrearProductoViewModel(useCase)
            // Datos de prueba
            viewModel.nombre = "Producto de prueba"
            viewModel.precio = 100.0
            viewModel.stock = 10
            viewModel.categoria = "Electrónica"
            viewModel.nombreProvedor = "Proveedor A"
            viewModel.ubicacionProveedor = LatLng(100.15, -100.12)
            viewModel.qr = "QR123"

            val context = Mockito.mock(Context::class.java)

            // Llamar al método bajo prueba
            val result = viewModel.guardarProducto()

            // Verificar que el producto se haya guardado correctamente
            assertEquals("", viewModel.nombre) // Verificar que los campos se limpien después de guardar
        }

    // Puedes agregar más tests para otros métodos del ViewModel aquí
}
