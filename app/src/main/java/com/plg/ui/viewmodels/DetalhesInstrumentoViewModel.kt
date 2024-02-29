package com.plg.ui.viewmodels

import android.app.Application
import androidx.compose.material3.SnackbarResult
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.plg.database.AppDatabase
import com.plg.model.Guitarra
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetalhesInstrumentoViewModel(application: Application) : AndroidViewModel(application) {


    private val guitarraDao = AppDatabase.instancia(application).guitarraDao()
    private val _guitarra = MutableStateFlow<Guitarra?>(null)


    val guitarra: StateFlow<Guitarra?> get() = _guitarra


    private fun definirGuitarraId(id: Long): Long {
        return id
    }

    fun definirGuitarra(id: Long) {
        viewModelScope.launch {
            _guitarra.value = guitarraDao.buscarGuitarraPorId(definirGuitarraId(id))

        }
    }

}