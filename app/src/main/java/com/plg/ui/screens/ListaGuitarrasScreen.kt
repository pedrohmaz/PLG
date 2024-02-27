package com.plg.ui.screens

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plg.database.AppDatabase
import com.plg.formatarParaReal
import com.plg.model.Guitarra
import com.plg.ui.components.GuitarraImagem
import com.plg.ui.theme.PLGTheme
import kotlinx.coroutines.flow.first

@Composable
fun ListaGuitarrasScreen(activity: ComponentActivity) {

    val guitarraDao = AppDatabase.instancia(activity).guitarraDao()
    val listaGuitarras = remember { mutableStateListOf<Guitarra>() }

    LaunchedEffect(Unit) {
        listaGuitarras.addAll(guitarraDao.buscarTodasAsGuitarras().first())
    }

    PLGTheme {
        Surface {
            LazyColumn(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Log.i("Lista", "ListaGuitarrasScreen: $listaGuitarras")
                items(items = listaGuitarras) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        shape = RoundedCornerShape(5),
                        elevation = CardDefaults.cardElevation(16.dp),

                        ) {
                        Row {
                            GuitarraImagem(
                                corpo = it.corpo,
                                braco = it.braco,
                                headstock = it.headstock,
                                escudo = it.escudo,
                                marcacoes = it.marcacoes,
                                pecas = it.pecas,
                                corCorpo = Color(it.corCorpo),
                                corBraco = Color(it.corBraco),
                                corHeadstock = Color(it.corHeadstock),
                                corEscudo = Color(it.corEscudo),
                                corMarcacoes = Color(it.corMarcacoes),
                                modifier = Modifier
                                    .size(150.dp)
                                    .padding(8.dp)
                                    .offset(x= 8.dp)
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
                                Text(it.nome, fontSize = 18.sp)
                                Text(
                                    text = "Valor: ${formatarParaReal(it.valor)}",
                                    fontSize = 16.sp
                                )

                            }

                        }
                    }
                }
            }
        }
    }

}