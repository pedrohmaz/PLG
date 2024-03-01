package com.plg.ui.viewmodels

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import com.plg.R
import com.plg.database.AppDatabase
import com.plg.model.Guitarra
import com.plg.ui.components.BotaoSelecionado
import com.plg.ui.theme.CorCorpoVermelho
import com.plg.ui.theme.CorEscalaEscura
import com.plg.ui.theme.CorEscudoOriginal
import com.plg.ui.theme.CorMarcacaoClara
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EditarInstrumentoViewModel(application: Application) : AndroidViewModel(application) {


    val guitarraDao = AppDatabase.instancia(application).guitarraDao()

    private val _guitarra = MutableStateFlow<Guitarra?>(null)
    val guitarra: StateFlow<Guitarra?> get() = _guitarra


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
    private val _corCorpo = MutableStateFlow(CorCorpoVermelho)
    val corCorpo: StateFlow<Color> get() = _corCorpo
    private val _corBraco = MutableStateFlow(CorEscalaEscura)
    val corBraco: StateFlow<Color> get() = _corBraco
    private val _corHeadstock = MutableStateFlow(CorMarcacaoClara)
    val corHeadstock: StateFlow<Color> get() = _corHeadstock
    private val _corEscudo = MutableStateFlow(CorEscudoOriginal)
    val corEscudo: StateFlow<Color> get() = _corEscudo
    private val _corMarcacoes = MutableStateFlow(CorMarcacaoClara)
    val corMarcacoes: StateFlow<Color> get() = _corMarcacoes
    private val _botaoSelecionado = MutableStateFlow(BotaoSelecionado.Corpo)
    val botaoSelecionado: StateFlow<BotaoSelecionado> get() = _botaoSelecionado

    private val _valorModelo = MutableStateFlow(2000f)
    val valorModelo: StateFlow<Float> get() = _valorModelo
    private val _valorEscala = MutableStateFlow(0f)
    val valorEscala: StateFlow<Float> get() = _valorEscala
    private val _valorHeadstock = MutableStateFlow(100f)
    val valorHeadstock: StateFlow<Float> get() = _valorHeadstock
    private val _valorEscudo = MutableStateFlow(200f)
    val valorEscudo: StateFlow<Float> get() = _valorEscudo
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
        _corCorpo.value = cor
    }

    private fun trocarCorEscudo(cor: Color, valor: Float) {
        _corEscudo.value = cor
        _valorEscudo.value = valor
        _valorTotal.value = atualizarValorTotal()
    }

    private fun trocarCorHeadstock(cor: Color, valor: Float) {
        _corHeadstock.value = cor
        _valorHeadstock.value = valor
        _valorTotal.value = atualizarValorTotal()
    }

    private fun trocarCorBraco(cor: Color, valor: Float) {
        _corBraco.value = cor
        _valorEscala.value = valor
        _valorTotal.value = atualizarValorTotal()
    }

    private fun trocarCorMarcacoes(cor: Color, valor: Float) {
        _corMarcacoes.value = cor
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

    fun definirDesenhoInicial(
        corpo: Int, braco: Int, headstock: Int, escudo: Int, marcacoes: Int,
        pecas: Int, corCorpo: Int, corBraco: Int, corHeadstock: Int,
        corEscudo: Int, corMarcacoes: Int, valor: Float, valorModelo: Float,
        valorEscala: Float, valorHeadstock: Float, valorEscudo: Float
    ) {
        _corpo.value = corpo
        _braco.value = braco
        _headstock.value = headstock
        _escudo.value = escudo
        _marcacoes.value = marcacoes
        _pecas.value = pecas
        _corCorpo.value = Color(corCorpo)
        _corBraco.value = Color(corBraco)
        _corHeadstock.value = Color(corHeadstock)
        _corEscudo.value = Color(corEscudo)
        _corMarcacoes.value = Color(corMarcacoes)
        modeloGuit = if (_corpo.value == R.drawable.strato_corpo) {
            GuitarraModelos.Strato
        } else GuitarraModelos.Tele
        _valorTotal.value = valor
        _valorModelo.value = valorModelo
        _valorEscala.value = valorEscala
        _valorHeadstock.value = valorHeadstock
        _valorEscudo.value = valorEscudo
        atualizarValorTotal()
    }

    fun buscarValor(valor: StateFlow<Float>) : Float{
        return valor.value
    }

}