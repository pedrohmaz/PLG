package com.plg.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.plg.R
import com.plg.function
import com.plg.ui.theme.Ice


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
    corFuncao: (BotaoSelecionado) -> Color
) {


    val espacamento = 25.dp
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Ice),

    )
    {
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = {
                aoClicarBotaoCorpo()
            }) {
            Icon(
                modifier = Modifier.scale(3f),
                tint = corFuncao(BotaoSelecionado.Corpo),
                painter = painterResource(id = R.drawable.icone_corpo),
                contentDescription = "Mudar cor do corpo"
            )
        }
        Spacer(modifier = Modifier.width(espacamento))
        IconButton(
            onClick = {
                aoClicarBotaoBraco()
            }) {
            Icon(
                modifier = Modifier.scale(4.5f),
                tint = corFuncao(BotaoSelecionado.Braco),
                painter = painterResource(id = R.drawable.icone_braco),
                contentDescription = "Mudar cor do braco"
            )
        }
        Spacer(modifier = Modifier.width(espacamento))
        IconButton(
            onClick = {
                aoClicarBotaoHeadstock()
            }) {
            Icon(
                modifier = Modifier.scale(3.5f),
                tint = corFuncao(BotaoSelecionado.Headstock),
                painter = painterResource(id = R.drawable.icone_headstock),
                contentDescription = "Mudar cor do headstock"
            )
        }
        Spacer(modifier = Modifier.width(espacamento))
        IconButton(
            onClick = {
                aoClicarBotaoEscudo()
            }) {
            Icon(
                modifier = Modifier.scale(3f),
                tint = corFuncao(BotaoSelecionado.Escudo),
                painter = painterResource(id = R.drawable.icone_escudo),
                contentDescription = "Mudar cor do escudo"
            )
        }
        Spacer(modifier = Modifier.width(espacamento))
        IconButton(
            onClick = {
                aoClicarBotaoMarcacoes()
            }) {
            Icon(
                modifier = Modifier.scale(1.5f),
                tint = corFuncao(BotaoSelecionado.Marcacoes),
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Mudar cor das marcações"
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
    }
}




