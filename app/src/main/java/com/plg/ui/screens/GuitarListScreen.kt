package com.plg.ui.screens

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
import com.plg.formatToReal
import com.plg.function
import com.plg.ui.components.GuitarImage
import com.plg.ui.theme.PLGTheme
import com.plg.ui.viewmodels.GlobalViewModel
import com.plg.ui.viewmodels.GuitarListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuitarListScreen(
    activity: ComponentActivity,
    onNavigateToInstrumentDetails: (Long) -> Unit,
    onNavigateToCustomizeInstrument: function
) {

    val viewModel: GuitarListViewModel by activity.viewModels()
    val globalViewModel: GlobalViewModel by activity.viewModels()

    val guitarList by viewModel.guitarList.collectAsState()
    val snackBar = remember { SnackbarHostState() }
    val guitarId by globalViewModel.guitarId.collectAsState()
    val userId by globalViewModel.userId.collectAsState()
    val removedGuitar by globalViewModel.removedGuitar.collectAsState()


    LaunchedEffect(Unit) {
        if (removedGuitar) {
            val guitar = viewModel.searchGuitarById(guitarId)
            if (guitar != null) {
                viewModel.removeGuitar(guitar.id)
                val result = snackBar.showSnackbar(
                    message = "Instrument ${guitar.name} removed.",
                    actionLabel = "Undo",
                    withDismissAction = true,
                    duration = SnackbarDuration.Long
                )
                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        viewModel.saveGuitar(guitar)
                        viewModel.updateList(userId)
                    }

                    SnackbarResult.Dismissed -> {
                    }
                }
            }
            globalViewModel.setIfGuitarWasRemoved(false)
        }
    }

    PLGTheme {
        viewModel.updateList(userId)
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
                    items(items = guitarList) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable { onNavigateToInstrumentDetails(it.id) },
                            shape = RoundedCornerShape(5),
                            elevation = CardDefaults.cardElevation(16.dp),

                            ) {
                            Row {
                                GuitarImage(
                                    body = it.body,
                                    neck = it.neck,
                                    headstock = it.headstock,
                                    shield = it.shield,
                                    inlays = it.inlays,
                                    pieces = it.pieces,
                                    bodyColor1 = Color(it.bodyColor1),
                                    bodyColor2 = Color(it.bodyColor2),
                                    neckColor = Color(it.neckColor),
                                    headstockColor = Color(it.headstockColor),
                                    shieldColor = Color(it.shieldColor),
                                    inlayColor = Color(it.inlayColor),
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
                                    Text(it.name, fontSize = 18.sp)
                                    Text(
                                        text = "Value: ${formatToReal(it.value)}",
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
                    onClick = { onNavigateToCustomizeInstrument() }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "New Instrument"
                    )
                }
            }
        }
    }

}