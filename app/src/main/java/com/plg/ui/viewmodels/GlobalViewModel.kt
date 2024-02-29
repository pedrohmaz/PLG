package com.plg.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GlobalViewModel : ViewModel() {

    private val _usuarioId = MutableStateFlow<Long>(0)
    val usuarioId: StateFlow<Long> get() = _usuarioId
    private val _guitarraRemovida = MutableStateFlow(false)
    val guitarraRemovida: StateFlow<Boolean> get() = _guitarraRemovida


    fun mudarUsuarioId(id: Long) {
        _usuarioId.value = id
    }

    private val _guitarraId = MutableStateFlow<Long>(0)
    val guitarraId: StateFlow<Long> get() = _guitarraId

    fun mudarGuitarraId(id: Long) {
        _guitarraId.value = id
    }

    fun definirSeGuitarraFoiRemovida(estado: Boolean){
        _guitarraRemovida.value = estado
    }


}