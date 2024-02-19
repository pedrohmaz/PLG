package com.plg.ui.screens


import androidx.activity.ComponentActivity
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plg.R
import com.plg.function
import com.plg.ui.theme.PLGTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(activity: ComponentActivity, aoNavegarParaCustomizarInstrumento: function) {

    var textoUsuario by remember { mutableStateOf("") }
    var textoSenha by remember { mutableStateOf("") }
    var mostrarSenha by remember { mutableStateOf(false) }

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
                            value = textoUsuario,

                            onValueChange = {
                                textoUsuario = it
                            },
                            label = { Text("Usuário") },
                            singleLine = true,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Sharp.AccountCircle,
                                    tint = Black,
                                    contentDescription = ""
                                )
                            }
                        )
                        TextField(
                            value = textoSenha,

                            onValueChange = {
                                textoSenha = it
                            },
                            label = { Text("Senha") },
                            singleLine = true,
                            visualTransformation = if (mostrarSenha) VisualTransformation.None
                            else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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
                                checked = mostrarSenha,
                                onCheckedChange = { mostrarSenha = !mostrarSenha },
                            )
                        }
                        Button(modifier = Modifier.scale(1.2f)
                            .padding(16.dp)
                            , onClick = { aoNavegarParaCustomizarInstrumento() }) {
                            Text("Entrar")
                        }
                    }
                    FloatingActionButton(
                        modifier = Modifier
                            .height(70.dp)
                            .width(190.dp)
                            .align(Alignment.BottomEnd)
                            .padding(16.dp),
                        onClick = { /*TODO*/ }) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Sharp.Add, contentDescription = "icone de adicionar")
                            Text(" Adicionar Usuário")
                        }

                    }
                }
            }
        }
    }
}

