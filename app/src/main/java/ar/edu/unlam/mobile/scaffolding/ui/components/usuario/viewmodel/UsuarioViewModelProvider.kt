package ar.edu.unlam.mobile.scaffolding.ui.components.usuario.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import ar.edu.unlam.mobile.scaffolding.ScaffoldingApplication

object UsuarioViewModelProvider {
    val Factory =
        viewModelFactory {
            // Other Initializers
            // Initializer for ItemEntryViewModel
            initializer {
                UsuarioViewModel(usuarioApplication().container.usuarioRepository)
            }
        }
}

fun CreationExtras.usuarioApplication(): ScaffoldingApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ScaffoldingApplication)
