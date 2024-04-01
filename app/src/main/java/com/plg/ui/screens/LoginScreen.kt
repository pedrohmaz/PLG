package com.plg.ui.screens


import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AccountCircle
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.plg.R
import com.plg.function
import com.plg.ui.theme.PLGTheme
import com.plg.ui.viewmodels.GlobalViewModel
import com.plg.ui.viewmodels.LoginViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    activity: ComponentActivity,
    aoNavegarParaCustomizarInstrumento: function,
    aoNavegarParaCriarUsuario: function,
    aoNavegarParaListaUsuarios: function
) {

    val globalViewModel: GlobalViewModel by activity.viewModels()
    val viewModel: LoginViewModel by activity.viewModels()

    val coroutineScope = rememberCoroutineScope()

    val textoUsuario = viewModel.textoUsuario.collectAsState()
    val textoSenha = viewModel.textoSenha.collectAsState()
    val mostrarSenha = viewModel.mostrarSenha.collectAsState()


    PLGTheme {
        Surface(
            color = colorScheme.background
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        {
                            Text(text = "Login")
                        }, colors =
                        TopAppBarDefaults.mediumTopAppBarColors(colorScheme.primaryContainer)
                    )
                })
            { innerPadding ->
                Box(Modifier.fillMaxSize())
                {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(innerPadding),
                        //verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier.scale(0.8f),
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = ""
                        )
                        TextField(
                            value = textoUsuario.value,

                            onValueChange = {
                                viewModel.digitarUsuario(it)
                            },
                            label = { Text("Usuário") },
                            singleLine = true,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Sharp.AccountCircle,
                                    tint = Black,
                                    contentDescription = ""
                                )
                            },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                        )
                        TextField(
                            value = textoSenha.value,

                            onValueChange = {
                                viewModel.digitarSenha(it)
                            },
                            label = { Text("Senha") },
                            singleLine = true,
                            visualTransformation = if (mostrarSenha.value) VisualTransformation.None
                            else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Sharp.Lock,
                                    tint = Black,
                                    contentDescription = ""
                                )
                            }
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Mostrar senha", fontSize = 15.sp)
                            Checkbox(
                                checked = mostrarSenha.value,
                                onCheckedChange = { viewModel.clicarMostrarSenha() },
                            )
                        }
                        Button(modifier = Modifier
                            .scale(1.2f)
                            .padding(16.dp),
                            onClick = {
                                coroutineScope.launch {
                                    viewModel.autenticarLogin(
                                        textoUsuario.value,
                                        textoSenha.value,
                                        context = activity
                                    ) { sucesso ->
                                        coroutineScope.launch {
                                            if (sucesso) {
                                                globalViewModel.mudarAdmin(false)
                                                globalViewModel.mudarUsuarioId(
                                                    textoUsuario.value
                                                )
                                                aoNavegarParaCustomizarInstrumento()
                                            } else {
                                                if (textoUsuario.value == "PLGadmin" && textoSenha.value == "luthier123") {
                                                    globalViewModel.mudarAdmin(true)
                                                    aoNavegarParaListaUsuarios()
                                                }else {
                                                    Toast.makeText(
                                                        activity,
                                                        "Usuário ou senha incorretos.",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            }
                                        }
                                    }
                                }
                            })
                        {
                            Text("Entrar")
                        }
                    }
                    FloatingActionButton(
                        modifier = Modifier
                            .height(70.dp)
                            .width(190.dp)
                            .align(Alignment.BottomEnd)
                            .padding(16.dp),
                        onClick = { aoNavegarParaCriarUsuario() }
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Sharp.Add,
                                contentDescription = "icone de adicionar"
                            )
                            Text(" Adicionar Usuário")
                        }
                    }
                }
            }
        }
    }
}


