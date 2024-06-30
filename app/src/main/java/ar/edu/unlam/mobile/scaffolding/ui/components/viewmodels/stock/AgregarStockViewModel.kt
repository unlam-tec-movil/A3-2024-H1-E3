package ar.edu.unlam.mobile.scaffolding.ui.components.viewmodels.stock

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.domain.usecases.productos.AgregarStockUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AgregarStockViewModel
    @Inject
    constructor(
        private val agregarStockUseCase: AgregarStockUseCase,
    ) : ViewModel() {
        var stock by mutableIntStateOf(0)
        var qr by mutableStateOf("")

        suspend fun agregarStock() {
            agregarStockUseCase.agregarStock(
                this.stock,
                this.qr,
            )
            reestablecerValores()
        }

        private fun reestablecerValores() {
            this.stock = 0
            this.qr = ""
        }
    }
