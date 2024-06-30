package ar.edu.unlam.mobile.scaffolding.domain.usecases.user

import ar.edu.unlam.mobile.scaffolding.data.repository.usuario.OfflineUsuarioRepository

class GuardarUsuarioUseCase(
    private val repository: OfflineUsuarioRepository,
) {
    suspend fun guardarUsuario(
        nombre: String,
        negocio: String,
    ) {
        repository.guardarUsuario(nombre, negocio)
    }
}
