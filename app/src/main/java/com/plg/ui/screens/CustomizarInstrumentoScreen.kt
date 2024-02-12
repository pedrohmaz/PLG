package com.plg.ui.screens

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.plg.R
import com.plg.ui.components.GuitarraImagem
import com.plg.ui.components.OpcoesDoInstrumentoMenu
import com.plg.ui.theme.CorCorpoCreme
import com.plg.ui.theme.CorCorpoSunburst
import com.plg.ui.theme.CorCorpoVerde
import com.plg.ui.theme.CorCorpoVermelho
import com.plg.ui.theme.PLGTheme
import com.plg.ui.viewmodels.CustomizarInstrumentoViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomizarInstrumentoScreen(activity: ComponentActivity) {

    PLGTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val viewModel: CustomizarInstrumentoViewModel by activity.viewModels()
            val parteSelecionada =
                remember { mutableStateOf<(Color) -> Unit>({ viewModel.trocarCorCorpo(it) }) }

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
                    corMarcacoes.value,
                    corEscudo.value,
                    corHeadstock.value
                )
                Spacer(modifier = Modifier.height(16.dp))
                OpcoesDoInstrumentoMenu(
                    aoClicarBotaoVerde = { parteSelecionada.value(CorCorpoVerde) },
                    aoClicarBotaoVermelho = { parteSelecionada.value(CorCorpoVermelho) },
                    aoClicarBotaoCreme = { parteSelecionada.value(CorCorpoCreme) },
                    aoClicarBotaoSunburst = { parteSelecionada.value(CorCorpoSunburst) },
                    aoClicarBotaoModelo = { viewModel.trocarModelo() }
                )
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
                                        parteSelecionada.value = { viewModel.trocarCorCorpo(it) }
                                    }) {
                                    Icon(
                                        modifier = Modifier.scale(3f),
                                        painter = painterResource(id = R.drawable.icone_corpo),
                                        contentDescription = "Mudar cor do corpo"
                                    )
                                }
                                Spacer(modifier = Modifier.width(espacamento))
                                IconButton(
                                    onClick = {
                                        parteSelecionada.value = { viewModel.trocarCorBraco(it) }
                                    }) {
                                    Icon(
                                        modifier = Modifier.scale(4.5f),
                                        painter = painterResource(id = R.drawable.icone_braco),
                                        contentDescription = "Mudar cor do braco"
                                    )
                                }
                                Spacer(modifier = Modifier.width(espacamento))
                                IconButton(
                                    onClick = {
                                        parteSelecionada.value =
                                            { viewModel.trocarCorHeadstock(it) }
                                    }) {
                                    Icon(
                                        modifier = Modifier.scale(3.5f),
                                        painter = painterResource(id = R.drawable.icone_headstock),
                                        contentDescription = "Mudar cor do headstock"
                                    )
                                }
                                Spacer(modifier = Modifier.width(espacamento))
                                IconButton(
                                    onClick = {
                                        parteSelecionada.value = { viewModel.trocarCorEscudo(it) }
                                    }) {
                                    Icon(
                                        modifier = Modifier.scale(3f),
                                        painter = painterResource(id = R.drawable.icone_escudo),
                                        contentDescription = "Mudar cor do escudo"
                                    )
                                }
                                Spacer(modifier = Modifier.width(espacamento))
                                IconButton(
                                    onClick = {
                                        parteSelecionada.value =
                                            { viewModel.trocarCorMarcacoes(it) }
                                    }) {
                                    Icon(
                                        modifier = Modifier.scale(1.5f),
                                        imageVector = Icons.Default.MoreVert,
                                        contentDescription = "Mudar cor das marcações"
                                    )
                                }
                            })
                    }

                )
            }
        }
    }
}






