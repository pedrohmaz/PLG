package com.plg.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.plg.database.AppDatabase
import com.plg.model.Guitarra
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SalvarInstrumentoViewModel(application: Application): AndroidViewModel(application) {

    private val guitarraDao = AppDatabase.instancia(application).guitarraDao()

    private val _textoNome = MutableStateFlow("")
    val textoNome: StateFlow<String> get() = _textoNome
    fun salvarGuitarra(guitarra: Guitarra){
        viewModelScope.launch {
            guitarraDao.salvarGuitarra(guitarra)
        }
    }

    fun mudarTexto(texto: String){
        _textoNome.value = texto
    }

}