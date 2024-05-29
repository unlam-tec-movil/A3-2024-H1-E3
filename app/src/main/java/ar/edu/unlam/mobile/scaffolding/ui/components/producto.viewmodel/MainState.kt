package ar.edu.unlam.mobile.scaffolding.ui.components.producto.viewmodel

import ar.edu.unlam.mobile.scaffolding.data.local.producto.entity.Producto

data class MainState(
    val nombre: String = "",
    val precio: Double = 0.0,
    val stock: Int = 0,
    val categoria: String = "",
    val nombreProvedor: String = "",
    val qr: String = "",
    val productos: List<Producto> = emptyList(),
)