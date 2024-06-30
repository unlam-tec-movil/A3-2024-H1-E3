package ar.edu.unlam.mobile.scaffolding.ui.components.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.domain.usecases.user.GuardarUsuarioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel
    @Inject
    constructor(
        private val guardarUsuarioUseCase: GuardarUsuarioUseCase,
    ) : ViewModel() {
        var nombre by mutableStateOf("")
        var negocio by mutableStateOf("")

        suspend fun guardarUsuario() {
            guardarUsuarioUseCase.guardarUsuario(nombre, negocio)
        }
    }
