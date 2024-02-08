package com.plg.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.unit.dp
import com.plg.GuitarraBracos
import com.plg.GuitarraCores
import com.plg.GuitarraModelos
import com.plg.bracoGuit
import com.plg.corGuit
import com.plg.escolherFotoBraco
import com.plg.escolherFotoCorpo
import com.plg.modeloGuit
import com.plg.ui.theme.Orange

@Composable
fun OpcoesDoInstrumentoMenu(corpo: MutableState<Int>, braco: MutableState<Int>) {
    val sunburst = listOf(Orange, Black)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .border(2.dp, Black, RoundedCornerShape(8))


    ) {
        Row(modifier = Modifier.align(Center)) {
            Column(
                modifier = Modifier
                    .align(CenterVertically)
            )
            {
                Button(
                    onClick = {
                        bracoGuit = if (bracoGuit == GuitarraBracos.Escuro) GuitarraBracos.Claro
                         else GuitarraBracos.Escuro
                        aoClicarNoBotao(braco, escolherFotoBraco())
                    },
                    modifier = Modifier.align(CenterHorizontally)
                ) {
                    Text(text = "Mudar Escala")
                }
                Button(
                    onClick = {
                        modeloGuit = if(modeloGuit == GuitarraModelos.Strato) GuitarraModelos.Tele
                        else GuitarraModelos.Strato
                        aoClicarNoBotao(corpo, escolherFotoCorpo())
                        aoClicarNoBotao(braco, escolherFotoBraco())
                    },
                    modifier = Modifier.align(CenterHorizontally)
                ) {
                    Text(text = "Mudar Modelo")
                }
            }

            Spacer(modifier = Modifier.size(88.dp))

            Column(Modifier.align(CenterVertically)) {
                Row {
                    Button(
                        onClick = {
                            corGuit = GuitarraCores.Verde
                            aoClicarNoBotao(corpo, escolherFotoCorpo())
                        },
                        modifier = Modifier
                            .border(2.dp, Black, CircleShape)
                            .size(32.dp),
                        colors = ButtonDefaults.buttonColors(Green)

                    ) {
                    }

                    Spacer(modifier = Modifier.width(24.dp))

                    Button(
                        onClick = {
                            corGuit = GuitarraCores.Vermelho
                            aoClicarNoBotao(corpo, escolherFotoCorpo())
                        },
                        modifier = Modifier
                            .border(2.dp, Black, CircleShape)
                            .size(32.dp),
                        colors = ButtonDefaults.buttonColors(Red)
                    ) {
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row {
                    Button(
                        onClick = {
                            corGuit = GuitarraCores.Creme
                            aoClicarNoBotao(corpo, escolherFotoCorpo())
                        },
                        modifier = Modifier
                            .border(2.dp, Black, CircleShape)
                            .size(32.dp),
                        colors = ButtonDefaults.buttonColors(Yellow)
                    ) {
                    }

                    Spacer(modifier = Modifier.width(24.dp))

                    Button(
                        onClick = {
                            corGuit = GuitarraCores.Sunburst
                            aoClicarNoBotao(corpo, escolherFotoCorpo())
                        },
                        modifier = Modifier
                            .border(2.dp, Black, CircleShape)
                            .background(Brush.horizontalGradient(sunburst), CircleShape)
                            .size(32.dp),
                        colors = ButtonDefaults.buttonColors(Transparent)
                    ) {
                    }
                }
            }
        }
    }
}


fun aoClicarNoBotao(parte: MutableState<Int>, res: Int) {
    parte.value = res
}


//@Preview(showBackground = true)
//@Composable
//fun OpcoesDoInstrumentoMenuPreview() {
//    OpcoesDoInstrumentoMenu()
//}