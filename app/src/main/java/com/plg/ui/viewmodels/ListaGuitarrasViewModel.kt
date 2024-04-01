package com.plg.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import com.plg.model.Guitarra
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ListaGuitarrasViewModel(application: Application) : AndroidViewModel(application) {

    private val remoteDb = Firebase.firestore
    private val _listaGuitarras = MutableStateFlow<List<Guitarra>>(emptyList())
    val listaGuitarras: StateFlow<List<Guitarra>> get() = _listaGuitarras
    fun atualizarLista(id: String) {
        viewModelScope.launch {
            Log.i("TAG", "atualizarLista: $id")
            remoteDb.collection("Guitarras").whereEqualTo("usuario", id)
                .get().addOnSuccessListener {
                    _listaGuitarras.value = it.toObjects()
                }
        }
    }

    fun salvarGuitarra(guitarra: Guitarra) {
        viewModelScope.launch {
            remoteDb.collection("Guitarras").add(guitarra)
        }
    }

    fun removerGuitarra(id: Long) {
        viewModelScope.launch {
           val query = remoteDb.collection("Guitarras").whereEqualTo("id", id).get().await()
           val docRef = query.first()
           remoteDb.collection("Guitarras").document(docRef.id).delete()
        }
    }

    suspend fun buscarGuitarraPorId(id: Long): Guitarra? {
        var guitarra: Guitarra? = null
        viewModelScope.async {
            remoteDb.collection("Guitarras").whereEqualTo("id", id).get().addOnSuccessListener {
                guitarra = it.first().toObject()
            }.await()
        }.await()
        return guitarra
    }


}
