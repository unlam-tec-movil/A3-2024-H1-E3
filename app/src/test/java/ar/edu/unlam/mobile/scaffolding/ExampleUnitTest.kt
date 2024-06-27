package ar.edu.unlam.mobile.scaffolding

import ar.edu.unlam.mobile.scaffolding.data.local.usuario.dao.UsuarioDao
import ar.edu.unlam.mobile.scaffolding.data.repository.usuario.OfflineUsuarioRepository
import ar.edu.unlam.mobile.scaffolding.domain.usecases.user.GuardarUsuarioUseCase
import ar.edu.unlam.mobile.scaffolding.domain.usecases.user.ObtenerUsuarioUseCase
import ar.edu.unlam.mobile.scaffolding.ui.components.viewmodels.SplashViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.viewmodels.WelcomeViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private val dao = mock(UsuarioDao::class.java)
    private val repo = OfflineUsuarioRepository(dao)
    private val guardarUsuarioUseCase = GuardarUsuarioUseCase(repo)
    private val obtenerUsuarioUseCase = ObtenerUsuarioUseCase(repo)

    @Test
    fun queSeGuardeLaSesion() =
        runTest {
            val viewModel = WelcomeViewModel(guardarUsuarioUseCase)
            val splash = SplashViewModel(obtenerUsuarioUseCase)

            // Verificar que no exista ninguna sesion
            var valorEsperado = 0
            `when`(dao.getCantidadUsuarios()).thenReturn(valorEsperado)
            var valorObtenido = splash.getCantidadUsuarios()
            assertEquals(valorEsperado, valorObtenido)

            // Verificar que se haya creado una sesion
            viewModel.nombre = "Test"
            viewModel.negocio = "TestMarket"
            viewModel.guardarUsuario()

            valorEsperado = 1
            `when`(dao.getCantidadUsuarios()).thenReturn(valorEsperado)
            valorObtenido = splash.getCantidadUsuarios()
            assertEquals(valorEsperado, valorObtenido)
        }
}
