package ar.edu.unlam.mobile.scaffolding

import ar.edu.unlam.mobile.scaffolding.data.local.usuario.dao.UsuarioDao
import ar.edu.unlam.mobile.scaffolding.data.repository.usuario.OfflineUsuarioRepository
import ar.edu.unlam.mobile.scaffolding.ui.components.usuario.viewmodel.UsuarioViewModel
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

    @Test
    fun queSeGuardeLaSesion() =
        runTest {
            val viewModel = UsuarioViewModel(repo)

            // Verificar que no exista ninguna sesion
            var valorEsperado = 0
            `when`(dao.getCantidadUsuarios()).thenReturn(valorEsperado)
            var valorObtenido = viewModel.getCantidadUsuarios()
            assertEquals(valorEsperado, valorObtenido)

            // Verificar que se haya creado una sesion
            val nombre = "Sesion"
            val negocio = "Test"
            viewModel.guardarUsuario(nombre = nombre, negocio = negocio)

            valorEsperado = 1
            `when`(dao.getCantidadUsuarios()).thenReturn(valorEsperado)
            valorObtenido = viewModel.getCantidadUsuarios()
            assertEquals(valorEsperado, valorObtenido)
        }
}
