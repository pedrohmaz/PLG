package com.plg.ui.viewmodels

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.lifecycle.ViewModel
import com.plg.R
import com.plg.ui.theme.CorCorpoVermelho
import com.plg.ui.theme.CorEscalaClara
import com.plg.ui.theme.CorEscalaEscura
import com.plg.ui.theme.CorMarcacaoClara
import com.plg.ui.theme.CorMarcacaoEscura
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
    private val _corMarcacoes = MutableStateFlow(ColorFilter.tint(CorMarcacaoClara))
    val corMarcacoes: StateFlow<ColorFilter> get() = _corMarcacoes


    private var modeloGuit = GuitarraModelos.Strato
    private var bracoGuit = GuitarraBracos.Escuro

   private enum class GuitarraBracos {
        Claro,
        Escuro
    }

   private enum class GuitarraModelos {
        Strato,
        Tele
    }

    fun escolherFotoBraco(): Int {
        return when (modeloGuit) {
            GuitarraModelos.Strato -> R.drawable.strato_braco
            GuitarraModelos.Tele -> R.drawable.tele_braco
        }
    }

    fun escolherFotoCorpo(): Int {
        return when (modeloGuit) {
            GuitarraModelos.Strato -> R.drawable.strato_corpo
            GuitarraModelos.Tele -> R.drawable.tele_corpo
        }
    }

    fun escolherFotoHeadstock(): Int {
        return when (modeloGuit) {
            GuitarraModelos.Strato -> R.drawable.strato_headstock
            GuitarraModelos.Tele -> R.drawable.tele_headstock
        }
    }

    fun escolherFotoMarcacoes(): Int {
        return when (modeloGuit) {
            GuitarraModelos.Strato -> R.drawable.strato_marcacoes
            GuitarraModelos.Tele -> R.drawable.tele_marcacoes
        }
    }

    fun escolherFotoEscudo(): Int {
        return when (modeloGuit) {
            GuitarraModelos.Strato -> R.drawable.strato_escudo
            GuitarraModelos.Tele -> R.drawable.tele_escudo
        }
    }

    fun escolherFotoPecas(): Int {
        return when (modeloGuit) {
            GuitarraModelos.Strato -> R.drawable.strato_pecas
            GuitarraModelos.Tele -> R.drawable.tele_pecas
        }
    }


    fun trocarCor(cor: Color) {
        _corCorpo.value = ColorFilter.tint(cor)
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

    fun trocarBraco() {
        if (bracoGuit == GuitarraBracos.Escuro) {
            _corBraco.value = ColorFilter.tint(CorEscalaClara)
            _corMarcacoes.value = ColorFilter.tint(CorMarcacaoEscura)
            bracoGuit = GuitarraBracos.Claro
        } else {
            _corBraco.value = ColorFilter.tint(CorEscalaEscura)
            _corMarcacoes.value = ColorFilter.tint(CorMarcacaoClara)
            bracoGuit = GuitarraBracos.Escuro
        }
    }

}