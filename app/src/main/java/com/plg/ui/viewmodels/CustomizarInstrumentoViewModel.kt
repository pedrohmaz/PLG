package com.plg.ui.viewmodels

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.lifecycle.ViewModel
import com.plg.R
import com.plg.ui.components.BotaoSelecionado
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
    private val _botaoSelecionado = MutableStateFlow(BotaoSelecionado.Corpo)
    val botaoSelecionado: StateFlow<BotaoSelecionado> get() = _botaoSelecionado

    private val _valorModelo = MutableStateFlow(2000f)
    private val _valorHeadstock = MutableStateFlow(0f)
    private val _valorEscala = MutableStateFlow(100f)
    private val _valorEscudo = MutableStateFlow(200f)
    private val _valorTotal = MutableStateFlow(atualizarValorTotal())
    val valorTotal: StateFlow<Float> get() = _valorTotal

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


    private fun trocarCorCorpo(cor: Color, valor: Float) {
        _corCorpo.value = ColorFilter.tint(cor)
    }

    private fun trocarCorEscudo(cor: Color, valor: Float) {
        _corEscudo.value = ColorFilter.tint(cor)
        _valorEscudo.value = valor
        _valorTotal.value = atualizarValorTotal()
    }

    private fun trocarCorHeadstock(cor: Color, valor: Float) {
        _corHeadstock.value = ColorFilter.tint(cor)
        _valorHeadstock.value = valor
        _valorTotal.value = atualizarValorTotal()
    }

    private fun trocarCorBraco(cor: Color, valor: Float) {
        _corBraco.value = ColorFilter.tint(cor)
        _valorEscala.value = valor
        _valorTotal.value = atualizarValorTotal()
    }

    private fun trocarCorMarcacoes(cor: Color, valor: Float) {
        _corMarcacoes.value = ColorFilter.tint(cor)
    }

    fun trocarModelo() {
        if (modeloGuit == GuitarraModelos.Strato) {
            modeloGuit = GuitarraModelos.Tele
            _valorModelo.value = 1500f
            _valorTotal.value = atualizarValorTotal()
        } else {
            modeloGuit = GuitarraModelos.Strato
            _valorModelo.value = 2000f
            _valorTotal.value = atualizarValorTotal()
        }
        _corpo.value = escolherFotoCorpo()
        _braco.value = escolherFotoBraco()
        _headstock.value = escolherFotoHeadstock()
        _escudo.value = escolherFotoEscudo()
        _marcacoes.value = escolherFotoMarcacoes()
        _pecas.value = escolherFotoPecas()
    }

    private fun atualizarValorTotal(): Float {
        return _valorModelo.value + _valorHeadstock.value + _valorEscala.value + _valorEscudo.value
    }

    fun trocarBotaoSelecionado(botao: BotaoSelecionado) {
        _botaoSelecionado.value = botao
    }

    fun trocarParteSelecionada(): (Color, Float) -> Unit {
        return when (_botaoSelecionado.value) {
            BotaoSelecionado.Corpo -> ::trocarCorCorpo
            BotaoSelecionado.Braco -> ::trocarCorBraco
            BotaoSelecionado.Headstock -> ::trocarCorHeadstock
            BotaoSelecionado.Escudo -> ::trocarCorEscudo
            BotaoSelecionado.Marcacoes -> ::trocarCorMarcacoes
        }
    }


}