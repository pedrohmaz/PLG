package com.plg.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObjects
import com.plg.model.Guitarra
import com.plg.model.Usuario
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class ListaUsuariosViewModel : ViewModel() {

    private val remoteDb = Firebase.firestore

    private val _listaUsuarios = MutableStateFlow<List<Usuario>>(
        emptyList()
    )
    val listaUsuarios: StateFlow<List<Usuario>> get() = _listaUsuarios

    fun atualizarLista() {
        remoteDb.collection("Usuarios").get().addOnSuccessListener {
            _listaUsuarios.value = it.toObjects()
        }
    }

    suspend fun contarInstrumentos(usuario: Usuario): Int {
        var lista = emptyList<Guitarra>()
        viewModelScope.async {
                remoteDb.collection("Guitarras").whereEqualTo("usuario", usuario.login).get().addOnSuccessListener {
                    lista = it.toObjects()
                }.await()
        }.await()
        return lista.size
    }

}