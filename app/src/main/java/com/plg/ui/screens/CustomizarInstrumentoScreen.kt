package com.plg.ui.screens

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.plg.function
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

    PLGTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val viewModel: CustomizarInstrumentoViewModel by activity.viewModels()
            val parteSelecionada =
                remember { mutableStateOf<(Color) -> Unit>({ viewModel.trocarCorCorpo(it) }) }
            val menuCores: MutableState<@Composable function> =
                remember { mutableStateOf(@Composable { MenuCoresCorpo(parteSelecionada) }) }


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
                Box {
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
                    FloatingActionButton(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        onClick = { viewModel.trocarModelo() }) {
                        Icon(
                            imageVector = Icons.Sharp.Refresh,
                            contentDescription = "Icone Seta"
                        )
                    }

                }
                Spacer(modifier = Modifier.height(16.dp))
                menuCores.value()

                MenuPartes(
                    aoClicarBotaoCorpo = {
                        parteSelecionada.value =
                            { viewModel.trocarCorCorpo(it) }
                        menuCores.value =
                            @Composable { MenuCoresCorpo(parteSelecionada) }
                    },
                    aoClicarBotaoBraco = {
                        parteSelecionada.value =
                            { viewModel.trocarCorBraco(it) }
                        menuCores.value =
                            @Composable { MenuCoresBraco(parteSelecionada) }
                    },
                    aoClicarBotaoHeadstock = {
                        parteSelecionada.value =
                            { viewModel.trocarCorHeadstock(it) }
                        menuCores.value =
                            @Composable { MenuCoresHeadstock(parteSelecionada) }
                    },
                    aoClicarBotaoEscudo = {
                        parteSelecionada.value =
                            { viewModel.trocarCorEscudo(it) }
                        menuCores.value =
                            @Composable { MenuCoresEscudo(parteSelecionada) }
                    },
                    aoClicarBotaoMarcacoes = {
                        parteSelecionada.value =
                            { viewModel.trocarCorMarcacoes(it) }
                        menuCores.value =
                            @Composable { MenuCoresMarcacoes(parteSelecionada) }
                    }
                )
            }
        }
    }


}







