package ar.edu.unlam.mobile.scaffolding.data.local.usuario.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey
    val id: Int = 0,
    val nombre: String,
    val negocio: String,
)
