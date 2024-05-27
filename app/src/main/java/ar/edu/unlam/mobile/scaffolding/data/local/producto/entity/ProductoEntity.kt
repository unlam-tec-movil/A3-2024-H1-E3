package ar.edu.unlam.mobile.scaffolding.data.local.producto.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "producto")
data class ProductoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val precio: Double,
    val stock: Int,
    val categoria: String,
    val nombreProvedor: String,
    val qr: String,
)
