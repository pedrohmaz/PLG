package com.plg.ui.viewmodels

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.lifecycle.ViewModel
import com.plg.R
import com.plg.ui.theme.CorCorpoVermelho
import com.plg.ui.theme.CorEscalaEscura
import com.plg.ui.theme.CorEscudoOriginal
import com.plg.ui.theme.CorMarcacaoClara
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CustomizarInstrumentoViewModel : ViewModel() {

    private val _corpo = MutableStateFlow(R.drawable.strato_corpo)
    val corpo: StateFlow<Int> get() = _corpo
    private val _braco = MutableStateFlow(R.drawable.strato_braco)
    val braco: StateFlow<Int> get() = _braco
    private val _headstock = MutableStateFlow(R.drawable.strato_headstock)
    val headstock: StateFlow<Int> get() = _headstock
    private val _escudo = MutableStateFlow(R.drawable.strato_escudo)
    val escudo: StateFlow<Int> get() = _escudo
    private val _marcacoes = MutableStateFlow(R.drawable.strato_marcacoes)
    val marcacoes: StateFlow<Int> get() = _marcacoes
    private val _pecas = MutableStateFlow(R.drawable.strato_pecas)
    val pecas: StateFlow<Int> get() = _pecas
    private val _corCorpo = MutableStateFlow(ColorFilter.tint(CorCorpoVermelho))
    val corCorpo: StateFlow<ColorFilter> get() = _corCorpo
    private val _corBraco = MutableStateFlow(ColorFilter.tint(CorEscalaEscura))
    val corBraco: StateFlow<ColorFilter> get() = _corBraco
    private val _corHeadstock = MutableStateFlow(ColorFilter.tint(CorMarcacaoClara))
    val corHeadstock: StateFlow<ColorFilter> get() = _corHeadstock
    private val _corEscudo = MutableStateFlow(ColorFilter.tint(CorEscudoOriginal))
    val corEscudo: StateFlow<ColorFilter> get() = _corEscudo
    private val _corMarcacoes = MutableStateFlow(ColorFilter.tint(CorMarcacaoClara))
    val corMarcacoes: StateFlow<ColorFilter> get() = _corMarcacoes

    private var modeloGuit = GuitarraModelos.Strato

   private enum class GuitarraModelos {
        Strato,
        Tele
    }

    private fun escolherFotoBraco(): Int {
        return when (modeloGuit) {
            GuitarraModelos.Strato -> R.drawable.strato_braco
            GuitarraModelos.Tele -> R.drawable.tele_braco
        }
    }

    private fun escolherFotoCorpo(): Int {
        return when (modeloGuit) {
            GuitarraModelos.Strato -> R.drawable.strato_corpo
            GuitarraModelos.Tele -> R.drawable.tele_corpo
        }
    }

    private fun escolherFotoHeadstock(): Int {
        return when (modeloGuit) {
            GuitarraModelos.Strato -> R.drawable.strato_headstock
            GuitarraModelos.Tele -> R.drawable.tele_headstock
        }
    }

    private fun escolherFotoMarcacoes(): Int {
        return when (modeloGuit) {
            GuitarraModelos.Strato -> R.drawable.strato_marcacoes
            GuitarraModelos.Tele -> R.drawable.tele_marcacoes
        }
    }

    private fun escolherFotoEscudo(): Int {
        return when (modeloGuit) {
            GuitarraModelos.Strato -> R.drawable.strato_escudo
            GuitarraModelos.Tele -> R.drawable.tele_escudo
        }
    }

    private fun escolherFotoPecas(): Int {
        return when (modeloGuit) {
            GuitarraModelos.Strato -> R.drawable.strato_pecas
            GuitarraModelos.Tele -> R.drawable.tele_pecas
        }
    }


    fun trocarCorCorpo(cor: Color) {
        _corCorpo.value = ColorFilter.tint(cor)
    }

    fun trocarCorEscudo(cor: Color) {
        _corEscudo.value = ColorFilter.tint(cor)
    }

    fun trocarCorHeadstock(cor: Color){
        _corHeadstock.value = ColorFilter.tint(cor)
    }

    fun trocarCorBraco(cor: Color){
        _corBraco.value = ColorFilter.tint(cor)
    }

    fun trocarCorMarcacoes(cor: Color){
        _corMarcacoes.value = ColorFilter.tint(cor)
    }

    fun trocarModelo() {
        modeloGuit = if (modeloGuit == GuitarraModelos.Strato) GuitarraModelos.Tele
        else GuitarraModelos.Strato
        _corpo.value = escolherFotoCorpo()
        _braco.value = escolherFotoBraco()
        _headstock.value = escolherFotoHeadstock()
        _escudo.value = escolherFotoEscudo()
        _marcacoes.value = escolherFotoMarcacoes()
        _pecas.value = escolherFotoPecas()
    }


}