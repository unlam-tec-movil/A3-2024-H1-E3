package ar.edu.unlam.mobile.scaffolding.data.repository.usuario

import ar.edu.unlam.mobile.scaffolding.data.local.usuario.dao.UsuarioDao
import ar.edu.unlam.mobile.scaffolding.data.local.usuario.entity.Usuario

class OfflineUsuarioRepository(private val usuarioDao: UsuarioDao) : UsuarioRepository {
    override suspend fun guardarUsuario(
        nombre: String,
        negocio: String,
    ) {
        val u =
            Usuario(
                nombre = nombre,
                negocio = negocio,
            )
        usuarioDao.guardarUsuario(u)
    }

    override suspend fun getCantidadUsuarios(): Int = usuarioDao.getCantidadUsuarios()

    override fun getUsuario(): Usuario {
        return usuarioDao.getUsuario().first()
    }
}
