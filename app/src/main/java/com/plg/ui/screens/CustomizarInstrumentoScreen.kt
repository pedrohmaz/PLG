package com.plg.ui.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.sharp.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plg.function
import com.plg.ui.components.BotaoSelecionado
import com.plg.ui.components.GuitarraImagem
import com.plg.ui.components.MenuCoresBraco
import com.plg.ui.components.MenuCoresCorpo
import com.plg.ui.components.MenuCoresEscudo
import com.plg.ui.components.MenuCoresHeadstock
import com.plg.ui.components.MenuCoresMarcacoes
import com.plg.ui.components.MenuPartes
import com.plg.ui.theme.PLGTheme
import com.plg.ui.viewmodels.CustomizarInstrumentoViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CustomizarInstrumentoScreen(activity: ComponentActivity) {

    val viewModel: CustomizarInstrumentoViewModel by activity.viewModels()
    val parteSelecionada = remember { viewModel.trocarParteSelecionada() }

    val menuCores: MutableState<@Composable function> =
        remember { mutableStateOf(@Composable { MenuCoresCorpo(parteSelecionada) }) }

    val botaoSelecionado = viewModel.botaoSelecionado.collectAsState()

    fun trocarMenuCores() {
        return when (botaoSelecionado.value) {
            BotaoSelecionado.Corpo -> menuCores.value =
                { MenuCoresCorpo(viewModel.trocarParteSelecionada()) }

            BotaoSelecionado.Braco -> menuCores.value =
                { MenuCoresBraco(viewModel.trocarParteSelecionada()) }

            BotaoSelecionado.Headstock -> menuCores.value =
                { MenuCoresHeadstock(viewModel.trocarParteSelecionada()) }

            BotaoSelecionado.Escudo -> menuCores.value =
                { MenuCoresEscudo(viewModel.trocarParteSelecionada()) }

            BotaoSelecionado.Marcacoes -> menuCores.value =
                { MenuCoresMarcacoes(viewModel.trocarParteSelecionada()) }
        }
    }

    val corpo = viewModel.corpo.collectAsState()
    val braco = viewModel.braco.collectAsState()
    val headstock = viewModel.headstock.collectAsState()
    val escudo = viewModel.escudo.collectAsState()
    val marcacoes = viewModel.marcacoes.collectAsState()
    val pecas = viewModel.pecas.collectAsState()
    val corCorpo = viewModel.corCorpo.collectAsState()
    val corBraco = viewModel.corBraco.collectAsState()
    val corHeadstock = viewModel.corHeadstock.collectAsState()
    val corMarcacoes = viewModel.corMarcacoes.collectAsState()
    val corEscudo = viewModel.corEscudo.collectAsState()
    val valor by viewModel.valorTotal.collectAsState()

    val configuration = LocalConfiguration.current
    val isHorizontal = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE


    fun escolherCorDoBotao(botao: BotaoSelecionado): Color {
        return if (botaoSelecionado.value == botao) {
            Color.Red
        } else {
            Color.Black
        }
    }

    var scale by remember { mutableStateOf(1f) }
    // var rotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, offsetChange, _ ->
        scale *= zoomChange
        // rotation += rotationChange
        offset += offsetChange
    }

    PLGTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                GuitarraImagem(
                    corpo.value,
                    braco.value,
                    headstock.value,
                    escudo.value,
                    marcacoes.value,
                    pecas.value,
                    corCorpo.value,
                    corBraco.value,
                    corMarcacoes.value,
                    corEscudo.value,
                    corHeadstock.value,
                    modifier = Modifier
                        .scale(if (isHorizontal) 1.5f else 1.2f)
                        .rotate(
                            if (isHorizontal) 90f
                            else 0f
                        )
                        .align(if (isHorizontal) Alignment.Center else Alignment.TopCenter)
                        .offset(
                            x = if (isHorizontal) (-40).dp else 0.dp,
                            y = if (isHorizontal) 0.dp else 50.dp
                        )
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale,
                            // rotationZ = rotation,
                            translationX = offset.x,
                            translationY = offset.y
                        )
                        .transformable(state = state)
                    //.background(Blue)
                )
                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp),
                    onClick = {  }) {
                    Row {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            modifier = Modifier.align(Alignment.CenterVertically),
                            text = " R\$${valor} ",
                            fontSize = 16.sp
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Botão valor / ir para tela 'salvar pedido'"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
                FloatingActionButton(
                    modifier = Modifier
                        .align(if (isHorizontal) Alignment.CenterEnd else Alignment.BottomEnd)
                        .offset(
                            if (isHorizontal) (-16).dp else (-16).dp,
                            if (isHorizontal) 4.dp else (-140).dp
                        ),
                    onClick = { viewModel.trocarModelo() }) {
                    Icon(
                        imageVector = Icons.Sharp.Refresh,
                        contentDescription = "Icone Seta"
                    )
                }
                Column(
                    Modifier.align(Alignment.BottomCenter)
                ) {
                    trocarMenuCores()
                    menuCores.value()

                    MenuPartes(
                        aoClicarBotaoCorpo = {
                            viewModel.trocarBotaoSelecionado(BotaoSelecionado.Corpo)
                        },
                        aoClicarBotaoBraco = {
                            viewModel.trocarBotaoSelecionado(BotaoSelecionado.Braco)
                        },
                        aoClicarBotaoHeadstock = {
                            viewModel.trocarBotaoSelecionado(BotaoSelecionado.Headstock)
                        },
                        aoClicarBotaoEscudo = {
                            viewModel.trocarBotaoSelecionado(BotaoSelecionado.Escudo)
                        },
                        aoClicarBotaoMarcacoes = {
                            viewModel.trocarBotaoSelecionado(BotaoSelecionado.Marcacoes)
                        },
                        corFuncao = ::escolherCorDoBotao
                    )
                }

            }
        }
    }
}







