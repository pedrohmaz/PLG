package com.plg.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.plg.database.AppDatabase
import com.plg.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class CriarUsuarioViewModel(application: Application) : AndroidViewModel(application) {


    private val dao = AppDatabase.instancia(application).usuarioDao()
    private val remoteDb = Firebase.firestore

    private val _textoUsuario = MutableStateFlow("")
    val textoUsuario: StateFlow<String> get() = _textoUsuario
    private val _textoSenha = MutableStateFlow("")
    val textoSenha: StateFlow<String> get() = _textoSenha
    private val _textoErro = MutableStateFlow("")
    val textoErro: StateFlow<String> get() = _textoErro

    fun digitarUsuario(texto: String) {
        _textoUsuario.value = texto
    }

    fun digitarSenha(texto: String) {
        _textoSenha.value = texto.replace(" ", "")
    }

    fun mostrarTextoAux(texto: String) {
        _textoErro.value = texto
    }

    fun salvarUsuario(usuario: Usuario) {
        viewModelScope.launch {
            val id = dao.salvarUsuario(usuario)
            remoteDb.collection("Usuarios").add(dao.buscarUsuario(id).first())
        }
    }

    suspend fun checarUsuarioNovo(nome: String): Boolean {
        val usuarios = dao.checarUsuarioExistente(nome).first()
        return usuarios.isEmpty()
    }

    fun resetarEstado() {
        _textoUsuario.value = ""
        _textoSenha.value = ""
        _textoErro.value = ""

    }

    fun senhaValida(): Boolean {
        return _textoSenha.value.length in 6..12 &&
                _textoSenha.value.any { it.isDigit() } &&
                _textoSenha.value.any { it.isUpperCase() }
    }

}