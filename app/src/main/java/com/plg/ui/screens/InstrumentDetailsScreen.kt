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
import com.plg.ui.components.GuitarImage
import com.plg.ui.theme.PLGTheme
import com.plg.ui.viewmodels.InstrumentDetailsViewModel
import com.plg.ui.viewmodels.GlobalViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstrumentDetailsScreen(
    activity: ComponentActivity,
    navController: NavHostController,
    guitarId: Long,
    onNavigateToEditInstrument: function
) {

    val viewModel: InstrumentDetailsViewModel by activity.viewModels()
    val globalViewModel: GlobalViewModel by activity.viewModels()

    val guitar by viewModel.guitar.collectAsState()
    var dialogOpen by remember { mutableStateOf(false) }
    val admin = globalViewModel.admin.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.setGuitar(guitarId)
        globalViewModel.changeGuitarId(guitarId)
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
                                contentDescription = "back"
                            )
                        }
                    },
                    title = { guitar?.let { Text(it.name) } },
                    colors =
                    TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primary),
                    actions = {
                        Row {
                            if (!admin.value) {
                                IconButton(onClick = { onNavigateToEditInstrument() }) {
                                    Icon(
                                        imageVector = Icons.Sharp.Edit,
                                        tint = White,
                                        contentDescription = "edit"
                                    )
                                }
                            }
                            IconButton(onClick = {
                                dialogOpen = true
                            })
                            {
                                Icon(
                                    imageVector = Icons.Sharp.Delete,
                                    contentDescription = "delete",
                                    tint = White
                                )
                            }
                        }
                    })
            })
        { innerPadding ->
            guitar?.let { guitarra ->
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    GuitarImage(
                        body = guitarra.body,
                        neck = guitarra.neck,
                        headstock = guitarra.headstock,
                        shield = guitarra.shield,
                        inlays = guitarra.inlays,
                        pieces = guitarra.pieces,
                        bodyColor1 = Color(guitarra.bodyColor1),
                        bodyColor2 = Color(guitarra.bodyColor2),
                        neckColor = Color(guitarra.neckColor),
                        headstockColor = Color(guitarra.headstockColor),
                        shieldColor = Color(guitarra.shieldColor),
                        inlayColor = Color(guitarra.inlayColor),
                        modifier = Modifier
                            .scale(1.2f)
                            .align(Alignment.BottomCenter)
                            .offset(y = (-55).dp)
                    )
                }
            }
            if (dialogOpen) {
                AlertDialog(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "warning"
                        )
                    },
                    title = { Text("Remove Instrument") },
                    text = { Text(text = "Are you sure you want to remove ${guitar?.name}?") },
                    onDismissRequest = { },
                    confirmButton = {
                        TextButton(onClick = {
                            navController.popBackStack()
                            globalViewModel.setIfGuitarWasRemoved(true)
                        }) {
                            Text("Confirm")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            dialogOpen = false
                        }) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
    }
}



