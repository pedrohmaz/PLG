package com.plg.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.plg.model.Guitar
import com.plg.model.remoteServer.RemoteDb
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SaveInstrumentViewModel(application: Application) : AndroidViewModel(application) {

    private val remoteDb = RemoteDb()

    private val _nameText = MutableStateFlow("")
    val nameText: StateFlow<String> get() = _nameText

    fun saveGuitar(guitar: Guitar) {
        viewModelScope.launch {
            remoteDb.addDocument("Guitars", guitar)
        }
    }

    suspend fun updateGuitar(guitar: Guitar) {
        val query = remoteDb.getElementByParam("Guitars","id", guitar.id)?.await()
        val docRef = query?.first()
        docRef?.id?.let { remoteDb.setDocument("Guitars", it, guitar) }
    }

    fun changeText(text: String?) {
        if (text != null) {
            _nameText.value = text
        }
    }
}
