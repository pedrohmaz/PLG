package com.plg.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.plg.R
import com.plg.ui.components.GuitarraImagem
import com.plg.ui.components.OpcoesDoInstrumentoMenu
import com.plg.ui.theme.CorCorpoVermelho
import com.plg.ui.theme.CorEscalaEscura
import com.plg.ui.theme.CorMarcacaoClara
import com.plg.ui.theme.PLGTheme

@Composable
fun CustomizarInstrumentoScreen() {


    PLGTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val corpo = remember { mutableStateOf(R.drawable.strato_corpo) }
            val braco = remember { mutableStateOf(R.drawable.strato_braco) }
            val headstock = remember { mutableStateOf(R.drawable.strato_headstock) }
            val escudo = remember { mutableStateOf(R.drawable.strato_escudo) }
            val marcacoes = remember { mutableStateOf(R.drawable.strato_marcacoes) }
            val pecas = remember { mutableStateOf(R.drawable.strato_pecas) }
            val corCorpo = remember { mutableStateOf(ColorFilter.tint(CorCorpoVermelho)) }
            val corBraco = remember { mutableStateOf(ColorFilter.tint(CorEscalaEscura)) }
            val corMarcacoes = remember { mutableStateOf(ColorFilter.tint(CorMarcacaoClara)) }

            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(16.dp))
                GuitarraImagem(
                    corpo.value,
                    braco.value,
                    headstock.value,
                    escudo.value,
                    marcacoes.value,
                    pecas.value,
                    corCorpo.value,
                    corBraco.value,
                    corMarcacoes.value
                )
                Spacer(modifier = Modifier.height(16.dp))
                OpcoesDoInstrumentoMenu(
                    corpo,
                    braco,
                    headstock,
                    escudo,
                    marcacoes,
                    pecas,
                    corCorpo,
                    corBraco,
                    corMarcacoes
                )

            }
        }
    }

}


