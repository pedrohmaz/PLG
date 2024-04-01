package com.plg.ui.screens

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plg.formatarParaReal
import com.plg.function
import com.plg.ui.components.GuitarraImagem
import com.plg.ui.theme.PLGTheme
import com.plg.ui.viewmodels.GlobalViewModel
import com.plg.ui.viewmodels.ListaGuitarrasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaGuitarrasScreen(
    activity: ComponentActivity,
    aoNavegarParaDetalhesInstrumento: (Long) -> Unit,
    aoNavegarParaCustomizarInstrumento: function
) {

    val viewModel: ListaGuitarrasViewModel by activity.viewModels()
    val globalViewModel: GlobalViewModel by activity.viewModels()

    val listaGuitarras by viewModel.listaGuitarras.collectAsState()
    val snackBar = remember { SnackbarHostState() }
    val guitarraId by globalViewModel.guitarraId.collectAsState()
    val usuarioId by globalViewModel.usuarioId.collectAsState()
    val guitarraRemovida by globalViewModel.guitarraRemovida.collectAsState()


    LaunchedEffect(Unit) {
        if (guitarraRemovida) {
            val guitarra = viewModel.buscarGuitarraPorId(guitarraId)
            if (guitarra != null) {
                viewModel.removerGuitarra(guitarra.id)
                val resultado = snackBar.showSnackbar(
                    message = "Instrumento ${guitarra.nome} removido.",
                    actionLabel = "Desfazer",
                    withDismissAction = true,
                    duration = SnackbarDuration.Long
                )
                when (resultado) {
                    SnackbarResult.ActionPerformed -> {
                        viewModel.salvarGuitarra(guitarra)
                        viewModel.atualizarLista(usuarioId)
                    }

                    SnackbarResult.Dismissed -> {
                    }
                }
            }
            globalViewModel.definirSeGuitarraFoiRemovida(false)
        }
    }

    PLGTheme {
        viewModel.atualizarLista(usuarioId)
        Scaffold(snackbarHost = {
            SnackbarHost(hostState = snackBar)
        }) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                LazyColumn(
                    Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(items = listaGuitarras) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable { aoNavegarParaDetalhesInstrumento(it.id) },
                            shape = RoundedCornerShape(5),
                            elevation = CardDefaults.cardElevation(16.dp),

                            ) {
                            Row {
                                GuitarraImagem(
                                    corpo = it.corpo,
                                    braco = it.braco,
                                    headstock = it.headstock,
                                    escudo = it.escudo,
                                    marcacoes = it.marcacoes,
                                    pecas = it.pecas,
                                    corCorpo = Color(it.corCorpo),
                                    corBraco = Color(it.corBraco),
                                    corHeadstock = Color(it.corHeadstock),
                                    corEscudo = Color(it.corEscudo),
                                    corMarcacoes = Color(it.corMarcacoes),
                                    modifier = Modifier
                                        .size(150.dp)
                                        .padding(8.dp)
                                        .offset(x = 8.dp)
                                )
                                Spacer(
                                    modifier = Modifier
                                        .height(120.dp)
                                        .width(1.dp)
                                        .align(CenterVertically)
                                        .offset(x = (-24).dp)
                                        .border(1.dp, Black)
                                )

                                Column(
                                    modifier = Modifier.offset(y = 32.dp),
                                    verticalArrangement = Arrangement.spacedBy(16.dp)
                                )
                                {
                                    Text(it.nome, fontSize = 18.sp)
                                    Text(
                                        text = "Valor: ${formatarParaReal(it.valor)}",
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        }
                    }
                }
                FloatingActionButton(  modifier = Modifier
                    .align(BottomEnd)
                    .padding(16.dp),
                    onClick = { aoNavegarParaCustomizarInstrumento() }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Novo Instrumento"
                    )
                }
            }
        }
    }

}