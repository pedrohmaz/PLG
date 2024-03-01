package com.plg.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.plg.formatarParaReal
import com.plg.function
import com.plg.model.Guitarra
import com.plg.ui.components.GuitarraImagem
import com.plg.ui.theme.PLGTheme
import com.plg.ui.viewmodels.GlobalViewModel
import com.plg.ui.viewmodels.SalvarInstrumentoViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalvarInstrumentoScreen(
    activity: ComponentActivity,
    navController: NavHostController,
    aoNavegarParaListaGuitarras: function,
    a: Int, b: Int, c: Int, d: Int, e: Int,
    f: Int, g: Int, h: Int, i: Int, j: Int,
    k: Int, l: Float, m: Float, n: Float, o: Float, p: Float, q: String, r: Long
) {

    val viewModel: SalvarInstrumentoViewModel by activity.viewModels()
    val globalViewModel: GlobalViewModel by activity.viewModels()
    val textoNome by viewModel.textoNome.collectAsState()

LaunchedEffect(Unit){
    viewModel.mudarTexto(q)
    if (q != "blank") viewModel.mudarTexto(q)
    else viewModel.mudarTexto("")
}
    PLGTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Salvar Instrumento") },
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
                        text = "Dê um nome para este instrumento:",
                        fontSize = 18.sp
                    )
                    OutlinedTextField(
                        modifier = Modifier.height(52.dp),
                        singleLine = true,
                        value = textoNome,
                        onValueChange = { textoNovo ->
                            val textoAntigo = textoNome
                            if (textoNovo.length <= 20) viewModel.mudarTexto(textoNovo)
                            else viewModel.mudarTexto(textoAntigo)
                        },
                    )
                    GuitarraImagem(
                        corpo = a,
                        braco = b,
                        headstock = c,
                        escudo = d,
                        marcacoes = e,
                        pecas = f,
                        corCorpo = Color(g),
                        corBraco = Color(h),
                        corHeadstock = Color(i),
                        corEscudo = Color(j),
                        corMarcacoes = Color(k),
                        modifier = Modifier
                            .size(400.dp)
                            .wrapContentSize()

                    )
                    Text(
                        text = "Valor final: ${formatarParaReal(l)}",
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
                            Text(text = "Voltar")
                        }
                        Button(onClick = {
                            var nome = textoNome
                            if (nome.isBlank()) nome = "Minha Guitarra"
                            val guitarra = Guitarra(
                                a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, nome, r, globalViewModel.guitarraId.value
                            )
                            viewModel.salvarGuitarra(guitarra)
                            Toast.makeText(activity, "Guitarra salva.", Toast.LENGTH_SHORT)
                                .show()
                            aoNavegarParaListaGuitarras()
                        }) {
                            Text(text = "Salvar")
                        }
                    }

                }
            }

        }


    }

}
