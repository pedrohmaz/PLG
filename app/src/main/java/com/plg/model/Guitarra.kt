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
    val nome: String = "",
    val valor: Float = 0f,
    val usuario: Long = 0,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)
