package com.plg.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.plg.database.AppDatabase
import com.plg.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CriarUsuarioViewModel(application: Application) : AndroidViewModel(application) {


    private val dao = AppDatabase.instancia(application).usuarioDao()

    private val _textoUsuario = MutableStateFlow("")
    val textoUsuario: StateFlow<String> get() = _textoUsuario
    private val _textoSenha = MutableStateFlow("")
    val textoSenha: StateFlow<String> get() = _textoSenha

    fun digitarUsuario(texto: String){
        _textoUsuario.value = texto
    }

    fun digitarSenha(texto: String){
        _textoSenha.value = texto
    }

    fun salvarUsuario(usuario: Usuario){
        viewModelScope.launch{
            dao.salvarUsuario(usuario)
        }
    }
}