package com.plg.ui.screens

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.plg.model.Usuario
import com.plg.ui.theme.PLGTheme
import com.plg.ui.viewmodels.CriarUsuarioViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CriarUsuarioScreen(activity: ComponentActivity, navController: NavHostController) {

    val viewModel: CriarUsuarioViewModel by activity.viewModels()

    val coroutineScope = rememberCoroutineScope()

    val textoUsuario = viewModel.textoUsuario.collectAsState()
    val textoSenha = viewModel.textoSenha.collectAsState()
    val textoAux = viewModel.textoErro.collectAsState()
    var asteriscoNome: String by remember { mutableStateOf("") }
    var asteriscoSenha: String by remember { mutableStateOf("") }
    var corSenhaSupporting: Color by remember { mutableStateOf(Black) }
    val keyboardController = LocalSoftwareKeyboardController.current


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
                        Row {
                            Text("Nome de Usuário")
                            Text(text = asteriscoNome, color = Red)
                        }
                        TextField(
                            value = textoUsuario.value,
                            onValueChange = { viewModel.digitarUsuario(it) },
                            singleLine = true,
                            keyboardOptions = (KeyboardOptions(imeAction = ImeAction.Next))
                        )
                        Spacer(Modifier.height(32.dp))
                        Row {
                            Text("Senha")
                            Text(text = asteriscoSenha, color = Red)
                        }
                        TextField(
                            value = textoSenha.value,
                            supportingText = {
                                Text(
                                    modifier = Modifier.offset(x = (-16).dp),
                                    text =
                                    "A senha deve conter" +
                                            " entre 6 e 12 caracteres, pelo menos um" +
                                            " número e uma letra maiúscula.",
                                    color = corSenhaSupporting
                                )
                            },
                            onValueChange = {
                                viewModel.digitarSenha(it)
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                        )
                        Spacer(Modifier.height(32.dp))
                        Button(
                            modifier = Modifier.align(CenterHorizontally),
                            onClick = {
                                coroutineScope.launch {
                                    if (viewModel.checarUsuarioNovo(textoUsuario.value)) {
                                        viewModel.mostrarTextoAux("")
                                        if (viewModel.senhaValida()) {
                                            viewModel.salvarUsuario(
                                                Usuario(
                                                    login = textoUsuario.value,
                                                    senha = textoSenha.value
                                                )
                                            )
                                            keyboardController?.hide()
                                            navController.popBackStack()
                                            viewModel.resetarEstado()
                                            Toast.makeText(
                                                activity,
                                                "Usuário criado com sucesso",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            keyboardController?.hide()
                                            asteriscoSenha = "*"
                                            corSenhaSupporting = Red
                                        }
                                    } else {
                                        keyboardController?.hide()
                                        viewModel.mostrarTextoAux(
                                            "Nome de usúario já" +
                                                    " cadastrado. Por favor escolha um novo nome."
                                        )
                                        asteriscoNome = "*"
                                    }
                                }
                            }) {
                            Text("Criar Usuário")
                        }
                        Text(
                            modifier = Modifier.offset(x = (-8).dp, y = 16.dp),
                            text = textoAux.value,
                            color = Red,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}