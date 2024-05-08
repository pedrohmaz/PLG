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
import com.plg.isConnected
import com.plg.model.User
import com.plg.ui.theme.PLGTheme
import com.plg.ui.viewmodels.CreateUserViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CreateUserScreen(activity: ComponentActivity, navController: NavHostController) {

    val viewModel: CreateUserViewModel by activity.viewModels()

    val coroutineScope = rememberCoroutineScope()

    val userText = viewModel.userText.collectAsState()
    val passwordText = viewModel.passwordText.collectAsState()
    val auxText = viewModel.errorText.collectAsState()
    var nameAsterisk: String by remember { mutableStateOf("") }
    var passwordAsterisk: String by remember { mutableStateOf("") }
    var supportingPasswordColor: Color by remember { mutableStateOf(Black) }
    val keyboardController = LocalSoftwareKeyboardController.current


    PLGTheme {
        Surface {
            Scaffold(topBar = {
                TopAppBar(
                    title = { Text("Create User") },
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
                            Text(text = nameAsterisk, color = Red)
                        }
                        TextField(
                            value = userText.value,
                            onValueChange = { viewModel.typeUser(it) },
                            singleLine = true,
                            keyboardOptions = (KeyboardOptions(imeAction = ImeAction.Next))
                        )
                        Spacer(Modifier.height(32.dp))
                        Row {
                            Text("Password")
                            Text(text = passwordAsterisk, color = Red)
                        }
                        TextField(
                            value = passwordText.value,
                            supportingText = {
                                Text(
                                    modifier = Modifier.offset(x = (-16).dp),
                                    text =
                                    "The password must have" +
                                            " between 6 and 12 characters, at least one" +
                                            " number and one uppercase letter.",
                                    color = supportingPasswordColor
                                )
                            },
                            onValueChange = {
                                viewModel.typePassword(it)
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                        )
                        Spacer(Modifier.height(32.dp))
                        Button(
                            modifier = Modifier.align(CenterHorizontally),
                            onClick = {
                                coroutineScope.launch {
                                    if(isConnected(activity)) {
                                        if (viewModel.checkNewUser(userText.value)) {
                                            viewModel.showAuxText("")
                                            if (viewModel.validPassword()) {
                                                viewModel.saveUser(
                                                    User(
                                                        login = userText.value,
                                                        password = passwordText.value
                                                    )
                                                )
                                                keyboardController?.hide()
                                                navController.popBackStack()
                                                viewModel.resetState()
                                                Toast.makeText(
                                                    activity,
                                                    "User created successfully",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            } else {
                                                keyboardController?.hide()
                                                passwordAsterisk = "*"
                                                supportingPasswordColor = Red
                                            }
                                        } else {
                                            keyboardController?.hide()
                                            viewModel.showAuxText(
                                                "Username already" +
                                                        " exists. Please choose another one."
                                            )
                                            nameAsterisk = "*"
                                        }
                                    } else Toast.makeText(activity, "Could not create user. Internet unavailable.", Toast.LENGTH_SHORT).show()
                                }
                            }) {
                            Text("Create User")
                        }
                        Text(
                            modifier = Modifier.offset(x = (-8).dp, y = 16.dp),
                            text = auxText.value,
                            color = Red,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}