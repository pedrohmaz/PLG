package com.plg.ui.screens

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plg.ui.components.GuitarraImagem
import com.plg.ui.components.OpcoesDoInstrumentoMenu
import com.plg.ui.theme.PLGTheme
import com.plg.ui.viewmodels.CustomizarInstrumentoViewModel


@Composable
fun CustomizarInstrumentoScreen(activity: ComponentActivity) {

    PLGTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val viewModel: CustomizarInstrumentoViewModel by activity.viewModels()

            val corpo = viewModel.corpo.collectAsState()
            val braco = viewModel.braco.collectAsState()
            val headstock = viewModel.headstock.collectAsState()
            val escudo = viewModel.escudo.collectAsState()
            val marcacoes = viewModel.marcacoes.collectAsState()
            val pecas = viewModel.pecas.collectAsState()
            val corCorpo = viewModel.corCorpo.collectAsState()
            val corBraco = viewModel.corBraco.collectAsState()
            val corMarcacoes = viewModel.corMarcacoes.collectAsState()

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
                    aoClicarBotaoVerde = { viewModel.trocarCor(it) },
                    aoClicarBotaoVermelho = { viewModel.trocarCor(it) },
                    aoClicarBotaoCreme = { viewModel.trocarCor(it) },
                    aoClicarBotaoSunburst = { viewModel.trocarCor(it) },
                    aoClicarBotaoModelo = { viewModel.trocarModelo() },
                    aoClicarBotaoBraco = {viewModel.trocarBraco()}
                )
            }

        }
    }
}





