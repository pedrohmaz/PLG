package com.plg.ui.viewmodels

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObjects
import com.plg.database.AppDatabase
import com.plg.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.instancia(application).usuarioDao()
    private val remoteDb = Firebase.firestore

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

    suspend fun obterIdUsuario(nome: String): Long {
        return dao.checarUsuarioExistente(nome).first()[0].id
    }

    fun autenticarLogin(nome: String, senha: String, context: Context, callback: (Boolean) -> Unit) {
        remoteDb.collection("Usuarios").whereEqualTo("login", nome).whereEqualTo("senha", senha)
            .get().addOnSuccessListener {
                val usuarios: List<Usuario> = it.toObjects()
                callback(usuarios.isNotEmpty())
            }.addOnFailureListener {
                Toast.makeText(
                    context,
                    "Não foi possível acessar o servidor.",
                    Toast.LENGTH_SHORT
                ).show()
                callback(false)
            }
    }

}