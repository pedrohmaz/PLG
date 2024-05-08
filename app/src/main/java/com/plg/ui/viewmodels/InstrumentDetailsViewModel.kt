package com.plg.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.plg.model.Guitar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InstrumentDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val remoteDb = Firebase.firestore
    private val _guitar = MutableStateFlow<Guitar?>(null)

    val guitar: StateFlow<Guitar?> get() = _guitar

    private fun setGuitarId(id: Long): Long {
        return id
    }

    fun setGuitar(id: Long) {
        viewModelScope.launch {
            remoteDb.collection("Guitars").whereEqualTo("id", id).get().addOnSuccessListener {
                _guitar.value = it.first().toObject()
            }
        }
    }

}