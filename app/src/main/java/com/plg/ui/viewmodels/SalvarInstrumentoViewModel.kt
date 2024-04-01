package com.plg.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.plg.model.Guitarra
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SalvarInstrumentoViewModel(application: Application) : AndroidViewModel(application) {

    private val remoteDb = Firebase.firestore

    private val _textoNome = MutableStateFlow("")
    val textoNome: StateFlow<String> get() = _textoNome

    fun salvarGuitarra(guitarra: Guitarra) {
        viewModelScope.launch {
            remoteDb.collection("Guitarras").add(guitarra)
        }
    }

    suspend fun atualizarGuitarra(guitarra: Guitarra) {
        val query = remoteDb.collection("Guitarras").whereEqualTo("id", guitarra.id).get().await()
        val docRef = query.first()
        remoteDb.collection("Guitarras").document(docRef.id).set(guitarra)
    }

    fun mudarTexto(texto: String?) {
        if (texto != null) {
            _textoNome.value = texto
        }
    }
}
