package com.plg.ui.screens

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.Delete
import androidx.compose.material.icons.sharp.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.plg.function
import com.plg.ui.components.GuitarraImagem
import com.plg.ui.theme.PLGTheme
import com.plg.ui.viewmodels.DetalhesInstrumentoViewModel
import com.plg.ui.viewmodels.GlobalViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalhesInstrumentoScreen(
    activity: ComponentActivity,
    navController: NavHostController,
    guitarraId: Long,
    aoNavegarParaEditarInstrumento: function
) {

    val viewModel: DetalhesInstrumentoViewModel by activity.viewModels()
    val globalViewModel: GlobalViewModel by activity.viewModels()

    val guitarra by viewModel.guitarra.collectAsState()
    var dialogAberto by remember { mutableStateOf(false) }
    val admin = globalViewModel.admin.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.definirGuitarra(guitarraId)
        globalViewModel.mudarGuitarraId(guitarraId)
    }
    PLGTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Sharp.ArrowBack,
                                tint = White,
                                contentDescription = "voltar"
                            )
                        }
                    },
                    title = { guitarra?.let { Text(it.nome) } },
                    colors =
                    TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primary),
                    actions = {
                        Row {
                            if (!admin.value) {
                                IconButton(onClick = { aoNavegarParaEditarInstrumento() }) {
                                    Icon(
                                        imageVector = Icons.Sharp.Edit,
                                        tint = White,
                                        contentDescription = "editar"
                                    )
                                }
                            }
                            IconButton(onClick = {
                                dialogAberto = true
                            })
                            {
                                Icon(
                                    imageVector = Icons.Sharp.Delete,
                                    contentDescription = "deletar",
                                    tint = White
                                )
                            }
                        }
                    })
            })
        { innerPadding ->
            guitarra?.let { guitarra ->
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    GuitarraImagem(
                        corpo = guitarra.corpo,
                        braco = guitarra.braco,
                        headstock = guitarra.headstock,
                        escudo = guitarra.escudo,
                        marcacoes = guitarra.marcacoes,
                        pecas = guitarra.pecas,
                        corCorpo = Color(guitarra.corCorpo),
                        corBraco = Color(guitarra.corBraco),
                        corHeadstock = Color(guitarra.corHeadstock),
                        corEscudo = Color(guitarra.corEscudo),
                        corMarcacoes = Color(guitarra.corMarcacoes),
                        modifier = Modifier
                            .scale(1.2f)
                            .align(Alignment.BottomCenter)
                            .offset(y = (-55).dp)
                    )
                }
            }
            if (dialogAberto) {
                AlertDialog(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "atenção"
                        )
                    },
                    title = { Text("Remover Instrumento") },
                    text = { Text(text = "Tem certeza que deseja remover ${guitarra?.nome}?") },
                    onDismissRequest = { },
                    confirmButton = {
                        TextButton(onClick = {
                            navController.popBackStack()
                            globalViewModel.definirSeGuitarraFoiRemovida(true)
                        }) {
                            Text("Confirmar")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            dialogAberto = false
                        }) {
                            Text("Cancelar")
                        }
                    }
                )
            }
        }
    }
}



