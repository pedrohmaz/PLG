package com.plg.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class GlobalViewModel : ViewModel() {

    private val remoteDb = Firebase.firestore

    private val _usuarioId = MutableStateFlow("")
    val usuarioId: StateFlow<String> get() = _usuarioId
    private val _guitarraRemovida = MutableStateFlow(false)
    val guitarraRemovida: StateFlow<Boolean> get() = _guitarraRemovida

    private val _atualizandoGuitarra = MutableStateFlow(false)
    val atualizandoGuitarra: StateFlow<Boolean> get() = _atualizandoGuitarra

    private val _guitarraId = MutableStateFlow<Long>(0)
    val guitarraId: StateFlow<Long> get() = _guitarraId

    suspend fun definirId(): Long {
        var id: Long = 0
        viewModelScope.async {
            remoteDb.collection("Id").document("id").get().addOnSuccessListener {
                if (!it.exists()) {
                    val data = hashMapOf<String, Long>("valor" to 0)
                    remoteDb.collection("Id").document("id").set(data)
                } else {
                    val oldValue = it.getLong("valor") ?: 0
                    id = oldValue + 1
                    val data = hashMapOf("valor" to id)
                    remoteDb.collection("Id").document("id").set(data)
                }
            }.await()
        }.await()
        Log.i("Treta", "definirId: id = $id")
        return id
    }

    fun mudarUsuarioId(id: String) {
        _usuarioId.value = id
    }

    fun mudarGuitarraId(id: Long) {
        _guitarraId.value = id
    }

    fun definirSeGuitarraFoiRemovida(estado: Boolean) {
        _guitarraRemovida.value = estado
    }

    fun definirAtualizandoGuitarra(estado: Boolean){
        _atualizandoGuitarra.value = estado
    }

}