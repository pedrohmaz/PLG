package com.plg.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.plg.model.Guitarra
import com.plg.model.Usuario

@Database([Usuario::class, Guitarra::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
abstract fun usuarioDao(): UsuarioDao
abstract fun guitarraDao(): GuitarraDao

    companion object {
        @Volatile
        private var INSTANCIA: AppDatabase? = null

        fun instancia(context: Context): AppDatabase {
            return INSTANCIA ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCIA = instance
                instance
            }
        }
    }



}