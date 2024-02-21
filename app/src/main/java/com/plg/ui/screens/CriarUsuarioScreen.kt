package com.plg.ui.screens

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plg.model.Usuario
import com.plg.ui.theme.PLGTheme
import com.plg.ui.viewmodels.CriarUsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CriarUsuarioScreen(activity: ComponentActivity) {

    val viewModel: CriarUsuarioViewModel by activity.viewModels()

    val textoUsuario = viewModel.textoUsuario.collectAsState()
    val textoSenha = viewModel.textoSenha.collectAsState()

    PLGTheme {
        Surface {
            Scaffold(topBar = {
                TopAppBar(
                    title = { Text("Criar Usuário") },
                    colors =
                    TopAppBarDefaults.mediumTopAppBarColors
                        (MaterialTheme.colorScheme.primaryContainer)
                )
            }) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(innerPadding),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Column(Modifier.padding(48.dp)) {
                        Text("Nome de Usuário")
                        TextField(
                            value = textoUsuario.value,
                            onValueChange = { viewModel.digitarUsuario(it) },
                            singleLine = true
                        )
                        Spacer(Modifier.height(32.dp))
                        Text("Senha")
                        TextField(value = textoSenha.value,
                            supportingText = {
                                Text(
                                    modifier = Modifier.offset(x = (-16).dp),
                                    text =
                                    "A senha deve conter" +
                                            " entre 6 e 12 caracteres, pelo menos um" +
                                            " número e uma letra maiúscula."
                                )
                            },
                            onValueChange = { viewModel.digitarSenha(it) })
                        Spacer(Modifier.height(32.dp))
                        Button(
                            modifier = Modifier.align(CenterHorizontally),
                            onClick = {
                                viewModel.salvarUsuario(Usuario(
                                login = textoUsuario.value,
                                senha = textoSenha.value
                            ) )
                                  }) {
                            Text("Criar Usuário")
                        }
                    }
                }
            }
        }
    }
}