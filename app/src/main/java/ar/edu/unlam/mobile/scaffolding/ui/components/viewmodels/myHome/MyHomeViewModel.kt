package ar.edu.unlam.mobile.scaffolding.ui.components.viewmodels.myHome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.Producto
import ar.edu.unlam.mobile.scaffolding.domain.usecases.productos.ObtenerListaProductosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MyHomeViewModel
    @Inject
    constructor(
        private val obtenerListaProductosUseCase: ObtenerListaProductosUseCase,
    ) : ViewModel() {
        val productos: StateFlow<List<Producto>> =
            obtenerListaProductos()
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = emptyList(),
                )

        var producto: Producto? = null

        fun seleccionarProducto(producto: Producto) {
            this.producto = producto
        }

        private fun obtenerListaProductos(): Flow<List<Producto>> {
            return obtenerListaProductosUseCase.obtenerListaProductos()
        }
    }
