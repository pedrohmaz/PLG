package com.plg.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.plg.model.Guitarra
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetalhesInstrumentoViewModel(application: Application) : AndroidViewModel(application) {

    private val remoteDb = Firebase.firestore
    private val _guitarra = MutableStateFlow<Guitarra?>(null)

    val guitarra: StateFlow<Guitarra?> get() = _guitarra

    private fun definirGuitarraId(id: Long): Long {
        return id
    }

    fun definirGuitarra(id: Long) {
        viewModelScope.launch {
            remoteDb.collection("Guitarras").whereEqualTo("id", id).get().addOnSuccessListener {
                _guitarra.value = it.first().toObject()
            }
        }
    }

}