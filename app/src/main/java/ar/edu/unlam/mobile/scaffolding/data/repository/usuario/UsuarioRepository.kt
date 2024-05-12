package ar.edu.unlam.mobile.scaffolding.data.repository.usuario

import ar.edu.unlam.mobile.scaffolding.data.local.usuario.entity.Usuario

interface UsuarioRepository {
    suspend fun getCantidadUsuarios(): Int

    fun getUsuario(): Usuario

    suspend fun guardarUsuario(
        nombre: String,
        negocio: String,
    )
}
