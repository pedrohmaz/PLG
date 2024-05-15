package com.plg.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.plg.model.Guitar
import com.plg.model.remoteServer.RemoteDb
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InstrumentDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val remoteDb = RemoteDb()
    private val _guitar = MutableStateFlow<Guitar?>(null)

    val guitar: StateFlow<Guitar?> get() = _guitar

    fun setGuitar(id: Long) {
        viewModelScope.launch {
            remoteDb.getElementByParam("Guitars","id", id)?.addOnSuccessListener {
                _guitar.value = it.first().toObject()
            }
        }
    }

}