package com.plg.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.plg.model.Guitar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SaveInstrumentViewModel(application: Application) : AndroidViewModel(application) {

    private val remoteDb = Firebase.firestore

    private val _nameText = MutableStateFlow("")
    val nameText: StateFlow<String> get() = _nameText

    fun saveGuitar(guitar: Guitar) {
        viewModelScope.launch {
            remoteDb.collection("Guitars").add(guitar)
        }
    }

    suspend fun updateGuitar(guitar: Guitar) {
        val query = remoteDb.collection("Guitars").whereEqualTo("id", guitar.id).get().await()
        val docRef = query.first()
        remoteDb.collection("Guitars").document(docRef.id).set(guitar)
    }

    fun changeText(text: String?) {
        if (text != null) {
            _nameText.value = text
        }
    }
}
