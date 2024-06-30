package ar.edu.unlam.mobile.scaffolding.ui.components.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.domain.usecases.user.ObtenerUsuarioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
    @Inject
    constructor(
        private val obtenerUsuarioUseCase: ObtenerUsuarioUseCase,
    ) : ViewModel() {
        var cantidadUsuarios by mutableIntStateOf(0)

        suspend fun getCantidadUsuarios() {
            cantidadUsuarios = obtenerUsuarioUseCase.getCantidadUsuarios()
        }
    }
