package com.plg.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import com.plg.model.Guitar
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class GuitarListViewModel(application: Application) : AndroidViewModel(application) {

    private val remoteDb = Firebase.firestore
    private val _guitarList = MutableStateFlow<List<Guitar>>(emptyList())
    val guitarList: StateFlow<List<Guitar>> get() = _guitarList
    fun updateList(id: String) {
        viewModelScope.launch {
            remoteDb.collection("Guitars").whereEqualTo("user", id)
                .get().addOnSuccessListener {
                    _guitarList.value = it.toObjects()
                }
        }
    }

    fun saveGuitar(guitar: Guitar) {
        viewModelScope.launch {
            remoteDb.collection("Guitars").add(guitar)
        }
    }

    fun removeGuitar(id: Long) {
        viewModelScope.launch {
           val query = remoteDb.collection("Guitars").whereEqualTo("id", id).get().await()
           val docRef = query.first()
           remoteDb.collection("Guitars").document(docRef.id).delete()
        }
    }

    suspend fun searchGuitarById(id: Long): Guitar? {
        var guitar: Guitar? = null
        viewModelScope.async {
            remoteDb.collection("Guitars").whereEqualTo("id", id).get().addOnSuccessListener {
                guitar = it.first().toObject()
            }.await()
        }.await()
        return guitar
    }


}
