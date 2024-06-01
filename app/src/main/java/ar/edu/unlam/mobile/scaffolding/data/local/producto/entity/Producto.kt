package ar.edu.unlam.mobile.scaffolding.data.local.producto.entity

import com.google.android.gms.maps.model.LatLng

data class Producto(
    val nombre: String,
    val precio: Double,
    val stock: Int,
    val categoria: String,
    val nombreProvedor: String,
    val ubicacionProveedor: LatLng,
    val qr: String,
)
