package ar.edu.unlam.mobile.scaffolding.data.local.usuario

import android.content.Context
import ar.edu.unlam.mobile.scaffolding.data.local.database.InventoryDatabase
import ar.edu.unlam.mobile.scaffolding.data.repository.usuario.OfflineUsuarioRepository
import ar.edu.unlam.mobile.scaffolding.data.repository.usuario.UsuarioRepository

interface UsuarioContainer {
    val usuarioRepository: UsuarioRepository
}

class UsuarioDataContainer(
    private val contex: Context,
) : UsuarioContainer {
    override val usuarioRepository: UsuarioRepository by lazy {
        OfflineUsuarioRepository(InventoryDatabase.getDatabase(contex).usuarioDao())
    }
}
