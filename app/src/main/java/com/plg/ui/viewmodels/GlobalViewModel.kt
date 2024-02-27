package com.plg.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GlobalViewModel : ViewModel() {

    private val _usuarioId = MutableStateFlow<Long>(0)
    val usuarioId: StateFlow<Long> get() = _usuarioId

    fun mudarUsuarioId(id: Long) {
        _usuarioId.value = id
    }


}