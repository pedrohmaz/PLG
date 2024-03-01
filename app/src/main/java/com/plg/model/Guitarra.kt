package com.plg.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Guitarra(
    val corpo: Int,
    val braco: Int,
    val headstock: Int,
    val escudo: Int,
    val marcacoes: Int,
    val pecas: Int,
    val corCorpo: Int,
    val corBraco: Int,
    val corHeadstock: Int,
    val corEscudo: Int,
    val corMarcacoes: Int,
    val valor: Float = 0f,
    val valorModelo: Float = 0f,
    val valorEscala: Float = 0f,
    val valorHeadstock: Float = 0f,
    val valorEscudo: Float = 0f,
    val nome: String = "",
    val usuario: Long = 0,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)
