package com.plg.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import com.plg.model.Guitar
import com.plg.model.remoteServer.RemoteDb
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class GuitarListViewModel(application: Application) : AndroidViewModel(application) {


    private val remoteDb = RemoteDb()

    private val _guitarList = MutableStateFlow<List<Guitar>>(emptyList())
    val guitarList: StateFlow<List<Guitar>> get() = _guitarList
    fun updateList(id: String) {
        viewModelScope.launch {
            remoteDb.getElementByParam("Guitars", "user", id)?.addOnSuccessListener {
                    _guitarList.value = it.toObjects()
                }
        }
    }

    fun saveGuitar(guitar: Guitar) {
        viewModelScope.launch {
            remoteDb.addDocument("Guitars", guitar)
        }
    }

    fun removeGuitar(id: Long) {
        viewModelScope.launch {
           val query = remoteDb.getElementByParam("Guitars", "id", id)?.await()
           val docRef = query?.first()
            docRef?.id?.let { remoteDb.removeDocument("Guitars", it) }
        }
    }

    suspend fun searchGuitarById(id: Long): Guitar? {
        var guitar: Guitar? = null
        viewModelScope.async {
            remoteDb.getElementByParam("Guitars","id", id)?.addOnSuccessListener {
                guitar = it.first().toObject()
            }?.await()
        }.await()
        return guitar
    }


}
