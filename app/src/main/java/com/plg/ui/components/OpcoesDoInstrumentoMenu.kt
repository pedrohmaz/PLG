package com.plg.ui.components

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plg.R
import com.plg.ui.screens.CustomizarInstrumentoScreen

@Composable
fun OpcoesDoInstrumentoMenu(corpo: MutableState<Int>, braco: MutableState<Int>) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .border(3.dp, Black, RectangleShape)
    ) {
        Row(modifier = Modifier.align(Center)) {
            Column(
                modifier = Modifier
                    .align(CenterVertically)
            )
            {
                Button(
                    onClick = { aoClicarNoBotao(braco, R.drawable.braco_claro_strato) },
                    modifier = Modifier.align(CenterHorizontally)
                ) {
                    Text(text = "Escala Clara")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { aoClicarNoBotao(braco, R.drawable.braco_escuro_strato) },
                    modifier = Modifier.align(CenterHorizontally),
                ) {
                    Text(text = "Escala Escura")
                }
            }

            Spacer(modifier = Modifier.size(88.dp))

            Column(Modifier.align(CenterVertically)) {
                Row {
                    Button(
                        onClick = { aoClicarNoBotao(corpo, R.drawable.corpo_verde_strato) },
                        modifier = Modifier
                            .border(2.dp, Black, CircleShape)
                            .size(32.dp),
                        colors = ButtonDefaults.buttonColors(Green)

                    ) {
                    }

                    Spacer(modifier = Modifier.width(24.dp))

                    Button(
                        onClick = { aoClicarNoBotao(corpo, R.drawable.corpo_vermelho_tele) },
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
                        onClick = { aoClicarNoBotao(corpo, R.drawable.corpo_creme_tele) },
                        modifier = Modifier
                            .border(2.dp, Black, CircleShape)
                            .size(32.dp),
                        colors = ButtonDefaults.buttonColors(Yellow)
                    ) {
                    }

                    Spacer(modifier = Modifier.width(24.dp))

                    Button(
                        onClick = { aoClicarNoBotao(corpo, R.drawable.corpo_sunburst_strato) },
                        modifier = Modifier
                            .border(2.dp, Black, CircleShape)
                            .size(32.dp),
                        colors = ButtonDefaults.buttonColors(Black)

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