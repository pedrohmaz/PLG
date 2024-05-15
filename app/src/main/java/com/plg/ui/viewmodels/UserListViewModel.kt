package com.plg.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObjects
import com.plg.model.Guitar
import com.plg.model.User
import com.plg.model.remoteServer.RemoteDb
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class UserListViewModel : ViewModel() {

    private val remoteDb = RemoteDb()

    private val _userList = MutableStateFlow<List<User>>(
        emptyList()
    )
    val userList: StateFlow<List<User>> get() = _userList

    fun updateList() {
        remoteDb.getCollection("Users")?.addOnSuccessListener {
            _userList.value = it.toObjects()
        }
    }

    suspend fun countInstruments(user: User): Int {
        var list = emptyList<Guitar>()
        viewModelScope.async {
                remoteDb.getElementByParam("Guitars","user", user.login)?.addOnSuccessListener {
                    list = it.toObjects()
                }?.await()
        }.await()
        return list.size
    }

}