package com.plg.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.plg.database.AppDatabase
import com.plg.model.Guitarra
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ListaGuitarrasViewModel(application: Application) : AndroidViewModel(application) {

    private val guitarraDao = AppDatabase.instancia(application).guitarraDao()
    private val _listaGuitarras = MutableStateFlow<List<Guitarra>>(emptyList())
    val listaGuitarras: StateFlow<List<Guitarra>> get() = _listaGuitarras
    fun atualizarLista(id: Long){
        viewModelScope.launch {
            _listaGuitarras.value = guitarraDao.buscarGuitarrasDoUsuario(id).first()
        }
    }

    fun salvarGuitarra(guitarra: Guitarra) {
        viewModelScope.launch {
            guitarraDao.salvarGuitarra(guitarra)
        }
    }

    fun removerGuitarra(guitarra: Guitarra) {
        viewModelScope.launch {
            guitarraDao.removerrGuitarra(guitarra)
        }
    }

    suspend fun buscarGuitarraPorId(id: Long) : Guitarra? {
        var guitarra: Guitarra? = null
        viewModelScope.async {
            guitarra = guitarraDao.buscarGuitarraPorId(id)
        }.await()
        return guitarra
    }
}
