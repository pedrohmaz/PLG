package com.plg.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.plg.model.User
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class CreateUserViewModel(application: Application) : AndroidViewModel(application) {


    private val remoteDb = Firebase.firestore

    private val _userText = MutableStateFlow("")
    val userText: StateFlow<String> get() = _userText
    private val _passwordText = MutableStateFlow("")
    val passwordText: StateFlow<String> get() = _passwordText
    private val _errorText = MutableStateFlow("")
    val errorText: StateFlow<String> get() = _errorText

    fun typeUser(text: String) {
        _userText.value = text
    }

    fun typePassword(text: String) {
        _passwordText.value = text.replace(" ", "")
    }

    fun showAuxText(text: String) {
        _errorText.value = text
    }

    fun saveUser(user: User) {
        viewModelScope.launch {

            remoteDb.collection("Usuarios").document(user.login)
                .set(user)
        }
    }

    suspend fun checkNewUser(nome: String): Boolean {
        var user: User? = null
        viewModelScope.async {
            remoteDb.collection("Usuarios").document(nome).get().addOnSuccessListener {
                user = it.toObject()
            }.await()
        }.await()
        return user == null
    }

    fun resetState() {
        _userText.value = ""
        _passwordText.value = ""
        _errorText.value = ""

    }

    fun validPassword(): Boolean {
        return _passwordText.value.length in 6..12 &&
                _passwordText.value.any { it.isDigit() } &&
                _passwordText.value.any { it.isUpperCase() }
    }

}