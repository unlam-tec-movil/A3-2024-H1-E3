package ar.edu.unlam.mobile.scaffolding

import ar.edu.unlam.mobile.scaffolding.data.local.usuario.dao.UsuarioDao
import ar.edu.unlam.mobile.scaffolding.data.repository.usuario.OfflineUsuarioRepository
import ar.edu.unlam.mobile.scaffolding.domain.usecases.user.GuardarUsuarioUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class UsuarioRepositoryTest {
    private lateinit var mockedDao: UsuarioDao
    private lateinit var repository: OfflineUsuarioRepository

    @Before
    fun setup() {
        mockedDao = Mockito.mock(UsuarioDao::class.java)
        repository = OfflineUsuarioRepository(mockedDao)
    }

    @Test
    fun queSeGuardeElUsuario() {
        val guardarUsuarioUseCase = GuardarUsuarioUseCase(repository)

        runTest {
            // Verificar que no exista ningún usuario
            Mockito.`when`(mockedDao.getCantidadUsuarios()).thenReturn(0)
            var valorObtenido = mockedDao.getCantidadUsuarios()
            assertEquals(0, valorObtenido)

            guardarUsuarioUseCase.guardarUsuario(
                "test",
                "testMarket",
            )

            Mockito.`when`(mockedDao.getCantidadUsuarios()).thenReturn(1)
            valorObtenido = mockedDao.getCantidadUsuarios()
            assertEquals(1, valorObtenido)
        }
    }
}
