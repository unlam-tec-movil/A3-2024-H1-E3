package ar.edu.unlam.mobile.scaffolding.data.local.producto.entity

data class Producto(
    val nombre: String,
    val precio: Double,
    val stock: Int,
    val categoria: String,
    val nombreProvedor: String,
    val qr: String,
)
