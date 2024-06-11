package ar.edu.unlam.mobile.scaffolding.ui.components.usuario.viewmodel

import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.data.repository.usuario.UsuarioRepository

class UsuarioViewModel(
    private val usuarioRepository: UsuarioRepository,
) : ViewModel() {
    suspend fun getCantidadUsuarios(): Int = usuarioRepository.getCantidadUsuarios()

    suspend fun guardarUsuario(
        nombre: String,
        negocio: String,
    ) {
        usuarioRepository.guardarUsuario(nombre = nombre, negocio = negocio)
    }
}
