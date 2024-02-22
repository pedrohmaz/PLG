package com.plg.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.plg.database.AppDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.instancia(application).usuarioDao()

    private val _textoUsuario = MutableStateFlow("")
    val textoUsuario: StateFlow<String> get() = _textoUsuario
    private val _textoSenha = MutableStateFlow("")
    val textoSenha: StateFlow<String> get() = _textoSenha
    private val _mostrarSenha = MutableStateFlow(false)
    val mostrarSenha: StateFlow<Boolean> get() = _mostrarSenha


    fun digitarUsuario(texto: String) {
        _textoUsuario.value = texto
    }

    fun digitarSenha(texto: String) {
        _textoSenha.value = texto
    }

    fun clicarMostrarSenha() {
        _mostrarSenha.value = !_mostrarSenha.value
    }


    suspend fun autenticarLogin(nome: String, senha: String): Boolean {
        val usuarios = dao.confirmarUsuario(nome, senha).first()
        return usuarios.isNotEmpty()
    }

}