package com.plg.ui.screens

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plg.estaConectado
import com.plg.function
import com.plg.ui.theme.PLGTheme
import com.plg.ui.viewmodels.GlobalViewModel
import com.plg.ui.viewmodels.ListaUsuariosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaUsuariosScreen(activity: ComponentActivity, aoNavegarParaListaGuitarras: function) {

    val viewModel: ListaUsuariosViewModel by activity.viewModels()
    val globalViewModel: GlobalViewModel by activity.viewModels()
    val listaUsuarios by viewModel.listaUsuarios.collectAsState()

    val snackBar = remember { SnackbarHostState() }

    LaunchedEffect(Unit){
       viewModel.atualizarLista()
        if(!estaConectado(activity)){
            snackBar.showSnackbar(
                message = "As informações podem estar desatualizadas devido à falta de conecção.",
                duration = SnackbarDuration.Long
            )
        }
    }

    PLGTheme {
        Scaffold(   snackbarHost = {
            SnackbarHost(
                hostState = snackBar,
            )
        }, topBar = {
            TopAppBar(title = { Text(text = "Admin")},
                colors = TopAppBarDefaults.mediumTopAppBarColors(MaterialTheme.colorScheme.primary)
        )
    }){innerPadding ->
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding), verticalArrangement = Arrangement.spacedBy(8.dp)){
                items(items = listaUsuarios){
                    var numero by remember { mutableIntStateOf(0) }
                    LaunchedEffect(Unit){
                            numero = viewModel.contarInstrumentos(it)
                    }
                    Card(
                        Modifier
                            .height(100.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .clickable {
                                globalViewModel.mudarUsuarioId(it.login)
                                aoNavegarParaListaGuitarras()
                            },
                        elevation = CardDefaults.cardElevation(8.dp)
                    ){
                        Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically){
                            Text(modifier = Modifier
                                .width(200.dp)
                                .padding(horizontal = 16.dp), text = it.login, fontSize = 20.sp)
                            Text(modifier = Modifier.padding(horizontal = 8.dp), text = "($numero Instrumentos)", fontSize = 20.sp)
                        }
                    }
                }
            }
        }
}
}