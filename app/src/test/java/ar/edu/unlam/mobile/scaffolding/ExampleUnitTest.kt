package ar.edu.unlam.mobile.scaffolding

import ar.edu.unlam.mobile.scaffolding.data.repository.usuario.OfflineUsuarioRepository
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun que_se_guarde_un_usuario() {
        val repo = mock(OfflineUsuarioRepository::class.java)
    }
}
