package ar.edu.unlam.mobile.scaffolding.domain.usecases.user

import ar.edu.unlam.mobile.scaffolding.data.repository.usuario.OfflineUsuarioRepository

class ObtenerUsuarioUseCase(private val repository: OfflineUsuarioRepository) {
    suspend fun getCantidadUsuarios(): Int {
        return repository.getCantidadUsuarios()
    }
}
