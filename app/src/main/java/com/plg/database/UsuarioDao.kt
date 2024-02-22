package com.plg.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.plg.model.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun salvarUsuario(usuario: Usuario)

    @Query("SELECT * FROM usuario WHERE id = :id")
    fun buscarUsuario(id: Long): Flow<Usuario>

    @Query("SELECT * FROM usuario WHERE login = :nome AND senha = :senha ")
    fun confirmarUsuario(nome: String, senha: String): Flow<List<Usuario>>

    @Query("SELECT * FROM usuario WHERE login = :nome ")
    fun checarUsuarioExistente(nome: String): Flow<List<Usuario>>

}