package com.plg.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.plg.function
import com.plg.ui.theme.CorCorpoCreme
import com.plg.ui.theme.CorCorpoSunburst
import com.plg.ui.theme.CorCorpoVerde
import com.plg.ui.theme.CorCorpoVermelho
import com.plg.ui.theme.CorEscalaClara
import com.plg.ui.theme.CorEscalaEscura


@Composable
fun MenuCoresMutable(
    aoClicarBotao1: function,
    corBotao1: Color,
    aoClicarBotao2: function,
    corBotao2: Color,
    botao3Ativado: Boolean = false,
    aoClicarBotao3: function = {},
    corBotao3: Color = Transparent,
    botao4Ativado: Boolean = false,
    aoClicarBotao4: function = {},
    corBotao4: Color = Transparent,
    botao5Ativado: Boolean = false,
    aoClicarBotao5: function = {},
    corBotao5: Color = Transparent,
    botao6Ativado: Boolean = false,
    aoClicarBotao6: function = {},
    corBotao6: Color = Transparent
) {
    //val sunburst = listOf(Orange, Black)
    Box(
        modifier = Modifier
            .height(85.dp)
            .fillMaxWidth()
            .background(Black)

    ) {
        Row(
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
                .align(Center)
        ) {
            Button(
                onClick = {
                    aoClicarBotao1()
                },
                modifier = Modifier
                    .border(1.dp, White, CircleShape)
                    .background(corBotao1, CircleShape)
                    .size(40.dp),
                colors = ButtonDefaults.buttonColors(Transparent)

            ) {
            }

            Button(
                onClick = {
                    aoClicarBotao2()
                },
                modifier = Modifier
                    .border(1.dp, White, CircleShape)
                    .background(corBotao2, CircleShape)
                    .size(40.dp),
                colors = ButtonDefaults.buttonColors(Transparent)

            ) {
            }

            Button(
                onClick = {
                    aoClicarBotao3()
                },
                modifier = Modifier
                    .alpha(if (botao3Ativado) 1f else 0f)
                    .border(1.dp, White, CircleShape)
                    .background(corBotao3, CircleShape)
                    .size(40.dp),
                colors = ButtonDefaults.buttonColors(Transparent)
            ) {
            }

            Button(
                onClick = {
                    aoClicarBotao4()
                },
                modifier = Modifier
                    .alpha(if (botao4Ativado) 1f else 0f)
                    .border(1.dp, White, CircleShape)
                    .background(corBotao4, CircleShape)
                    .size(40.dp),
                colors = ButtonDefaults.buttonColors(Transparent),
            ) {
            }

            Button(
                onClick = {
                    aoClicarBotao5()
                },
                modifier = Modifier
                    .alpha(if (botao5Ativado) 1f else 0f)
                    .border(1.dp, White, CircleShape)
                    .background(corBotao5, CircleShape)
                    .size(40.dp),
                colors = ButtonDefaults.buttonColors(Transparent)
            ) {
            }

            Button(
                onClick = {
                    aoClicarBotao6()
                },
                modifier = Modifier
                    .alpha(if (botao6Ativado) 1f else 0f)
                    .border(1.dp, White, CircleShape)
                    .background(corBotao6, CircleShape)
                    .size(40.dp),
                colors = ButtonDefaults.buttonColors(Transparent)
            ) {
            }
        }
    }
}


@Composable
fun MenuCoresCorpo(parteSelecionada: MutableState<(Color) -> Unit>) {
    MenuCoresMutable(
        aoClicarBotao1 = { parteSelecionada.value(CorCorpoVerde) },
        corBotao1 = CorCorpoVerde,
        aoClicarBotao2 = { parteSelecionada.value(CorCorpoVermelho) },
        corBotao2 = CorCorpoVermelho,
        botao3Ativado = true,
        aoClicarBotao3 = { parteSelecionada.value(CorCorpoCreme) },
        corBotao3 = CorCorpoCreme,
        botao4Ativado = true,
        aoClicarBotao4 = { parteSelecionada.value(CorCorpoSunburst) },
        corBotao4 = CorCorpoSunburst,
        botao5Ativado = true,
        aoClicarBotao5 = { parteSelecionada.value(Color.Blue) },
        corBotao5 = Color.Blue,
        botao6Ativado = true,
        aoClicarBotao6 = { parteSelecionada.value(Black) },
        corBotao6 = Black
    )
}

@Composable
fun MenuCoresBraco(parteSelecionada: MutableState<(Color) -> Unit>) {
    MenuCoresMutable(
        aoClicarBotao1 = { parteSelecionada.value(CorEscalaClara) },
        corBotao1 = CorEscalaClara,
        aoClicarBotao2 = { parteSelecionada.value(CorEscalaEscura) },
        corBotao2 = CorEscalaEscura
    )
}

@Composable
fun MenuCoresHeadstock(parteSelecionada: MutableState<(Color) -> Unit>) {
    MenuCoresMutable(
        aoClicarBotao1 = { parteSelecionada.value(CorCorpoVerde) },
        corBotao1 = CorCorpoVerde,
        aoClicarBotao2 = { parteSelecionada.value(CorCorpoVermelho) },
        corBotao2 = CorCorpoVermelho,
        botao3Ativado = true,
        aoClicarBotao3 = { parteSelecionada.value(CorCorpoCreme) },
        corBotao3 = CorCorpoCreme,
        botao4Ativado = true,
        aoClicarBotao4 = { parteSelecionada.value(CorEscalaClara) },
        corBotao4 = CorEscalaClara,
        botao5Ativado = true,
        aoClicarBotao5 = { parteSelecionada.value(Color.Blue) },
        corBotao5 = Color.Blue,
        botao6Ativado = true,
        aoClicarBotao6 = { parteSelecionada.value(Black) },
        corBotao6 = Black
    )
}

@Composable
fun MenuCoresEscudo(parteSelecionada: MutableState<(Color) -> Unit>) {
    MenuCoresMutable(
        aoClicarBotao1 = { parteSelecionada.value(CorCorpoVerde) },
        corBotao1 = CorCorpoVerde,
        aoClicarBotao2 = { parteSelecionada.value(CorCorpoVermelho) },
        corBotao2 = CorCorpoVermelho,
        botao3Ativado = true,
        aoClicarBotao3 = { parteSelecionada.value(CorCorpoCreme) },
        corBotao3 = CorCorpoCreme,
        botao4Ativado = true,
        aoClicarBotao4 = { parteSelecionada.value(CorCorpoSunburst) },
        corBotao4 = CorCorpoSunburst,
        botao5Ativado = true,
        aoClicarBotao5 = { parteSelecionada.value(Color.Blue) },
        corBotao5 = Color.Blue,
        botao6Ativado = true,
        aoClicarBotao6 = { parteSelecionada.value(Black) },
        corBotao6 = Black
    )
}

@Composable
fun MenuCoresMarcacoes(parteSelecionada: MutableState<(Color) -> Unit>) {
    MenuCoresMutable(
        aoClicarBotao1 = { parteSelecionada.value(CorCorpoVerde) },
        corBotao1 = CorCorpoVerde,
        aoClicarBotao2 = { parteSelecionada.value(CorCorpoVermelho) },
        corBotao2 = CorCorpoVermelho,
        botao3Ativado = true,
        aoClicarBotao3 = { parteSelecionada.value(CorEscalaClara) },
        corBotao3 = CorEscalaClara,
        botao4Ativado = true,
        aoClicarBotao4 = { parteSelecionada.value(CorEscalaEscura) },
        corBotao4 = CorEscalaEscura,
        botao5Ativado = true,
        aoClicarBotao5 = { parteSelecionada.value(Black) },
        corBotao5 = Black
    )
}




//@Preview(showBackground = true)
//@Composable
//fun OpcoesDoInstrumentoMenuPreview() {
//    OpcoesDoInstrumentoMenu()
//}