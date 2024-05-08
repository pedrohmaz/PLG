package com.plg.ui.viewmodels

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObjects
import com.plg.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val remoteDb = Firebase.firestore

    private val _userText = MutableStateFlow("")
    val userText: StateFlow<String> get() = _userText
    private val _passwordText = MutableStateFlow("")
    val passwordText: StateFlow<String> get() = _passwordText
    private val _showPassword = MutableStateFlow(false)
    val showPassword: StateFlow<Boolean> get() = _showPassword


    fun typeUser(text: String) {
        _userText.value = text
    }

    fun typePassword(text: String) {
        _passwordText.value = text
    }

    fun clickShowPassword() {
        _showPassword.value = !_showPassword.value
    }

    suspend fun getUserId(name: String): String {
        var id = ""
        remoteDb.collection("Usuarios").document(name).get().addOnSuccessListener {
            id = it.id
        }.await()
        return id
    }

    fun autenticateLogin(
        name: String,
        password: String,
        context: Context,
        callback: (Boolean) -> Unit
    ) {
        remoteDb.collection("Usuarios").whereEqualTo("login", name).whereEqualTo("senha", password)
            .get().addOnSuccessListener {
                val users: List<User> = it.toObjects()
                callback(users.isNotEmpty())
            }.addOnFailureListener {
                Toast.makeText(
                    context,
                    "Não foi possível acessar o servidor.",
                    Toast.LENGTH_SHORT
                ).show()
                callback(false)
            }
    }

}