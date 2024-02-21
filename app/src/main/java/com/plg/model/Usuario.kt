package com.plg.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val login: String,
    val senha: String
)
