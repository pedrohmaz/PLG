package com.plg.ui.viewmodels

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObjects
import com.plg.R
import com.plg.model.User
import com.plg.model.remoteServer.RemoteDb
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val remoteDb = RemoteDb()

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

    fun autenticateLogin(
        name: String,
        password: String,
        context: Context,
        callback: (Boolean) -> Unit
    ) {
        remoteDb.getElementByParam("Users","login", name, "password", password)
            ?.addOnSuccessListener {
                val users: List<User> = it.toObjects()
                callback(users.isNotEmpty())
            }?.addOnFailureListener {
                Toast.makeText(
                    context,
                    R.string.could_not_access_server,
                    Toast.LENGTH_SHORT
                ).show()
                callback(false)
            }
    }

}