package com.plg.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.plg.R
import com.plg.formatToReal
import com.plg.function
import com.plg.model.Guitar
import com.plg.ui.components.GuitarImage
import com.plg.ui.theme.PLGTheme
import com.plg.ui.viewmodels.GlobalViewModel
import com.plg.ui.viewmodels.SaveInstrumentViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveInstrumentScreen(
    activity: ComponentActivity,
    navController: NavHostController,
    onNavigateToGuitarList: function,
    a: Int, b: Int, c: Int, d: Int, e: Int,
    f: Int, g: Int, h: Int, i: Int, j: Int,
    k: Int, l: Int, m: Float, n: Float, o: Float, p: Float, q: Float, r: String, s: String
) {

    val viewModel: SaveInstrumentViewModel by activity.viewModels()
    val globalViewModel: GlobalViewModel by activity.viewModels()
    val nameText by viewModel.nameText.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    val updatingGuitar by globalViewModel.updatingGuitar.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.changeText(r)
        if (r != "blank") viewModel.changeText(r)
        else viewModel.changeText("")
        Log.i("User Id", "SalvarInstrumentoScreen: ${globalViewModel.userId.value}")
    }
    PLGTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.save_instrument)) },
                    colors = TopAppBarDefaults.smallTopAppBarColors
                        (MaterialTheme.colorScheme.primary)
                )
            }
        ) { innerPadding ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {


                    Text(
                        text = stringResource(R.string.give_this_instrument_a_name),
                        fontSize = 18.sp
                    )
                    OutlinedTextField(
                        modifier = Modifier.height(52.dp),
                        singleLine = true,
                        value = nameText,
                        onValueChange = { newText ->
                            val oldText = nameText
                            if (newText.length <= 20) viewModel.changeText(newText)
                            else viewModel.changeText(oldText)
                        },
                    )
                    GuitarImage(
                        body = a,
                        neck = b,
                        headstock = c,
                        shield = d,
                        inlays = e,
                        pieces = f,
                        bodyColor1 = Color(g),
                        bodyColor2 = Color(h),
                        neckColor = Color(i),
                        headstockColor = Color(j),
                        shieldColor = Color(k),
                        inlayColor = Color(l),
                        modifier = Modifier
                            .size(400.dp)
                            .wrapContentSize()

                    )
                    Text(
                        text = stringResource(R.string.final_value, formatToReal(m)),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .height(28.dp)
                            .fillMaxWidth()
                            .background(LightGray)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = { navController.popBackStack() }) {
                            Text(text = stringResource(R.string.back))
                        }
                        Button(onClick = {
                            coroutineScope.launch {
                                var nome = nameText
                                val id: Long =
                                    if (updatingGuitar) globalViewModel.guitarId.value
                                    else globalViewModel.setId()
                                if (nome.isBlank()) nome = activity.getString(R.string.my_guitar)
                                val guitar = Guitar(
                                    a,
                                    b,
                                    c,
                                    d,
                                    e,
                                    f,
                                    g,
                                    h,
                                    i,
                                    j,
                                    k,
                                    l,
                                    m,
                                    n,
                                    o,
                                    p,
                                    q,
                                    nome,
                                    s,
                                    id
                                )
                                if (!updatingGuitar) {
                                    viewModel.saveGuitar(guitar)
                                    Toast.makeText(activity,
                                        R.string.guitar_saved, Toast.LENGTH_SHORT)
                                        .show()
                                } else {
                                    viewModel.updateGuitar(guitar)
                                    globalViewModel.setUpdatingGuitar(false)
                                }
                                onNavigateToGuitarList()
                            }
                        }) {
                            Text(text = stringResource(R.string.save))
                        }
                    }

                }
            }

        }


    }

}
