package com.plg.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.plg.model.Guitarra
import kotlinx.coroutines.flow.Flow

@Dao
interface GuitarraDao {

    @Query("SELECT * FROM guitarra")
    fun buscarTodasAsGuitarras(): Flow<List<Guitarra>>

    @Query("SELECT * FROM guitarra WHERE usuario = :usuarioId" )
    fun buscarGuitarrasDoUsuario(usuarioId: Long) : Flow<List<Guitarra>>

    @Query("SELECT * FROM guitarra WHERE id = :id")
    suspend fun buscarGuitarraPorId(id: Long): Guitarra

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvarGuitarra(guitarra: Guitarra) : Long

    @Delete
    suspend fun removerrGuitarra(guitarra: Guitarra)

}