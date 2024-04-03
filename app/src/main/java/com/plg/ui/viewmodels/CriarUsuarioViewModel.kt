package com.plg.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.plg.model.Usuario
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class CriarUsuarioViewModel(application: Application) : AndroidViewModel(application) {


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

            remoteDb.collection("Usuarios").document(usuario.login)
                .set(usuario)
        }
    }

    suspend fun checarUsuarioNovo(nome: String): Boolean {
        var usuario: Usuario? = null
        viewModelScope.async {
            remoteDb.collection("Usuarios").document(nome).get().addOnSuccessListener {
                Log.i("TAG", "checarUsuarioNovo: onSuccessListener Trigado")
                usuario = it.toObject()
                Log.i("TAG", "checarUsuarioNovo: $usuario")
            }.await()
        }.await()
        Log.i("TAG", "checarUsuarioNovo: $usuario")
        return usuario == null
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