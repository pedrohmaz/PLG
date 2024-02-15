package com.plg.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.plg.R
import com.plg.function


enum class BotaoSelecionado {
    Corpo,
    Braco,
    Escudo,
    Headstock,
    Marcacoes
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuPartes(
    aoClicarBotaoCorpo: function,
    aoClicarBotaoBraco: function,
    aoClicarBotaoHeadstock: function,
    aoClicarBotaoEscudo: function,
    aoClicarBotaoMarcacoes: function,
) {

val botaoSelecionado = remember { mutableStateOf(BotaoSelecionado.Corpo) }

    fun escolherCorDoBotao(botao: BotaoSelecionado) : Color {
        return if (botaoSelecionado.value == botao) {
            Color.Red
        } else {
            Color.Black
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {},
        bottomBar = {
            val espacamento = 25.dp
            BottomAppBar(
                actions = {
                    Spacer(modifier = Modifier.width(espacamento))
                    IconButton(
                        onClick = {
                            botaoSelecionado.value = BotaoSelecionado.Corpo
                            aoClicarBotaoCorpo()
                        }) {
                        Icon(
                            modifier = Modifier.scale(3f),
                            tint = escolherCorDoBotao(BotaoSelecionado.Corpo),
                            painter = painterResource(id = R.drawable.icone_corpo),
                            contentDescription = "Mudar cor do corpo"
                        )
                    }
                    Spacer(modifier = Modifier.width(espacamento))
                    IconButton(
                        onClick = {
                            botaoSelecionado.value = BotaoSelecionado.Braco
                            aoClicarBotaoBraco()
                        }) {
                        Icon(
                            modifier = Modifier.scale(4.5f),
                            tint = escolherCorDoBotao(BotaoSelecionado.Braco),
                            painter = painterResource(id = R.drawable.icone_braco),
                            contentDescription = "Mudar cor do braco"
                        )
                    }
                    Spacer(modifier = Modifier.width(espacamento))
                    IconButton(
                        onClick = {
                            botaoSelecionado.value = BotaoSelecionado.Headstock
                            aoClicarBotaoHeadstock()
                        }) {
                        Icon(
                            modifier = Modifier.scale(3.5f),
                            tint = escolherCorDoBotao(BotaoSelecionado.Headstock),
                            painter = painterResource(id = R.drawable.icone_headstock),
                            contentDescription = "Mudar cor do headstock"
                        )
                    }
                    Spacer(modifier = Modifier.width(espacamento))
                    IconButton(
                        onClick = {
                            botaoSelecionado.value = BotaoSelecionado.Escudo
                            aoClicarBotaoEscudo()
                        }) {
                        Icon(
                            modifier = Modifier.scale(3f),
                            tint = escolherCorDoBotao(BotaoSelecionado.Escudo),
                            painter = painterResource(id = R.drawable.icone_escudo),
                            contentDescription = "Mudar cor do escudo"
                        )
                    }
                    Spacer(modifier = Modifier.width(espacamento))
                    IconButton(
                        onClick = {
                            botaoSelecionado.value = BotaoSelecionado.Marcacoes
                            aoClicarBotaoMarcacoes()
                        }) {
                        Icon(
                            modifier = Modifier.scale(1.5f),
                            tint = escolherCorDoBotao(BotaoSelecionado.Marcacoes),
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Mudar cor das marcações"
                        )
                    }
                })
        }

    )
}

