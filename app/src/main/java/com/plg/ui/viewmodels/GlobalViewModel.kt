package com.plg.ui.viewmodels

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

    private val _userId = MutableStateFlow("")
    val userId: StateFlow<String> get() = _userId
    private val _removedGuitar = MutableStateFlow(false)
    val removedGuitar: StateFlow<Boolean> get() = _removedGuitar

    private val _updatingGuitar = MutableStateFlow(false)
    val updatingGuitar: StateFlow<Boolean> get() = _updatingGuitar

    private val _guitarId = MutableStateFlow<Long>(0)
    val guitarId: StateFlow<Long> get() = _guitarId

    private val _admin = MutableStateFlow(false)
    val admin: StateFlow<Boolean> get() = _admin

    suspend fun setId(): Long {
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
        return id
    }

    fun changeUserId(id: String) {
        _userId.value = id
    }

    fun changeGuitarId(id: Long) {
        _guitarId.value = id
    }

    fun setIfGuitarWasRemoved(state: Boolean) {
        _removedGuitar.value = state
    }

    fun setUpdatingGuitar(state: Boolean){
        _updatingGuitar.value = state
    }

    fun changeAdmin(state: Boolean){
        _admin.value = state
    }

}