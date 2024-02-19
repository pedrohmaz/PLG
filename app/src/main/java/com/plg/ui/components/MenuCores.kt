package com.plg.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.plg.function
import com.plg.ui.theme.CorCorpoAzul
import com.plg.ui.theme.CorCorpoCreme
import com.plg.ui.theme.CorCorpoMadeira
import com.plg.ui.theme.CorCorpoSunburst
import com.plg.ui.theme.CorCorpoVerde
import com.plg.ui.theme.CorCorpoVermelho
import com.plg.ui.theme.CorEscalaClara
import com.plg.ui.theme.CorEscalaEscura
import com.plg.ui.theme.CorMarcacaoClara
import com.plg.ui.theme.CorMarcacaoEscura


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
    corBotao6: Brush = Brush.horizontalGradient(listOf(Transparent, Transparent))
) {
    //val sunburst = listOf(Orange, Black)


    Row(
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(Black)
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


@Composable
fun MenuCoresCorpo(parteSelecionada: (Color, valor: Float) -> Unit) {
    MenuCoresMutable(
        aoClicarBotao1 = { parteSelecionada(CorCorpoVermelho, 0f) },
        corBotao1 = CorCorpoVermelho,
        aoClicarBotao2 = { parteSelecionada(CorCorpoVerde, 0f) },
        corBotao2 = CorCorpoVerde,
        botao3Ativado = true,
        aoClicarBotao3 = { parteSelecionada(CorCorpoCreme, 0f) },
        corBotao3 = CorCorpoCreme,
        botao4Ativado = true,
        aoClicarBotao4 = { parteSelecionada(Black, 0f) },
        corBotao4 = Black,
        botao5Ativado = true,
        aoClicarBotao5 = { parteSelecionada(CorCorpoAzul, 0f) },
        corBotao5 = CorCorpoAzul,
        botao6Ativado = true,
        aoClicarBotao6 = { parteSelecionada(CorCorpoMadeira, 0f) },
        corBotao6 = Brush.horizontalGradient(listOf(CorCorpoMadeira, CorCorpoMadeira))
    )
}

@Composable
fun MenuCoresBraco(parteSelecionada: (Color, valor: Float) -> Unit) {
    MenuCoresMutable(
        aoClicarBotao1 = { parteSelecionada(CorEscalaEscura, 100f) },
        corBotao1 = CorEscalaEscura,
        aoClicarBotao2 = { parteSelecionada(CorEscalaClara, 0f) },
        corBotao2 = CorEscalaClara,
    )
}

@Composable
fun MenuCoresHeadstock(parteSelecionada: (Color, valor: Float) -> Unit ) {
    MenuCoresMutable(
        aoClicarBotao1 = {
            parteSelecionada(CorCorpoVermelho, 150f)
        },
        corBotao1 = CorCorpoVermelho,
        aoClicarBotao2 = { parteSelecionada(CorCorpoVerde, 150f) },
        corBotao2 = CorCorpoVerde,
        botao3Ativado = true,
        aoClicarBotao3 = { parteSelecionada(CorCorpoCreme, 150f) },
        corBotao3 = CorCorpoCreme,
        botao4Ativado = true,
        aoClicarBotao4 = { parteSelecionada(Black, 150f) },
        corBotao4 = Black,
        botao5Ativado = true,
        aoClicarBotao5 = { parteSelecionada(CorCorpoAzul, 150f) },
        corBotao5 = CorCorpoAzul,
        botao6Ativado = true,
        aoClicarBotao6 = { parteSelecionada(CorEscalaClara, 0f) },
        corBotao6 = Brush.horizontalGradient(listOf(CorEscalaClara, CorEscalaClara))
    )
}

@Composable
fun MenuCoresEscudo(parteSelecionada: (Color, valor: Float) -> Unit) {
    MenuCoresMutable(
        aoClicarBotao1 = { parteSelecionada(CorCorpoVermelho, 200f) },
        corBotao1 = CorCorpoVermelho,
        aoClicarBotao2 = { parteSelecionada(CorCorpoVerde, 200f) },
        corBotao2 = CorCorpoVerde,
        botao3Ativado = true,
        aoClicarBotao3 = { parteSelecionada(CorCorpoCreme, 200f) },
        corBotao3 = CorCorpoCreme,
        botao4Ativado = true,
        aoClicarBotao4 = { parteSelecionada(Black, 200f) },
        corBotao4 = Black,
        botao5Ativado = true,
        aoClicarBotao5 = { parteSelecionada(CorCorpoAzul, 200f) },
        corBotao5 = CorCorpoAzul,
        botao6Ativado = true,
        aoClicarBotao6 = { parteSelecionada(Transparent, 0f ) },
        corBotao6 = Brush.horizontalGradient(listOf(Black, White, Black, White, Black, White, Black, White, Black, White, Black, White))
    )
}

@Composable
fun MenuCoresMarcacoes(parteSelecionada: (Color, valor: Float) -> Unit) {
    MenuCoresMutable(
        aoClicarBotao1 = { parteSelecionada(CorMarcacaoEscura, 0f) },
        corBotao1 = CorMarcacaoEscura,
        aoClicarBotao2 = { parteSelecionada(CorMarcacaoClara, 0f)},
        corBotao2 = CorMarcacaoClara,
        botao3Ativado = true,
        aoClicarBotao3 = { parteSelecionada(CorCorpoSunburst, 0f) },
        corBotao3 = CorCorpoSunburst,
    )
}


//@Preview(showBackground = true)
//@Composable
//fun OpcoesDoInstrumentoMenuPreview() {
//    OpcoesDoInstrumentoMenu()
//}