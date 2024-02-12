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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.plg.ui.theme.CorCorpoCreme
import com.plg.ui.theme.CorCorpoSunburst
import com.plg.ui.theme.CorCorpoVerde
import com.plg.ui.theme.CorCorpoVermelho
import com.plg.ui.theme.Orange

@Composable
fun OpcoesDoInstrumentoMenu(
    aoClicarBotaoVerde: (Color) -> Unit,
    aoClicarBotaoVermelho: (Color) -> Unit,
    aoClicarBotaoCreme: (Color) -> Unit,
    aoClicarBotaoSunburst: (Color) -> Unit,
    aoClicarBotaoModelo: () -> Unit,
    aoClicarBotaoBraco: () -> Unit
) {
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
                        aoClicarBotaoBraco()
                    },
                    modifier = Modifier.align(CenterHorizontally)
                ) {
                    Text(text = "Mudar Escala")
                }
                Button(
                    onClick = {
                        aoClicarBotaoModelo()

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
                            aoClicarBotaoVerde(CorCorpoVerde)
                        },
                        modifier = Modifier
                            .border(2.dp, Black, CircleShape)
                            .size(32.dp),
                        colors = ButtonDefaults.buttonColors(CorCorpoVerde)

                    ) {
                    }

                    Spacer(modifier = Modifier.width(24.dp))

                    Button(
                        onClick = {
                            aoClicarBotaoVermelho(CorCorpoVermelho)
                        },
                        modifier = Modifier
                            .border(2.dp, Black, CircleShape)
                            .size(32.dp),
                        colors = ButtonDefaults.buttonColors(CorCorpoVermelho)
                    ) {
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row {
                    Button(
                        onClick = {
                            aoClicarBotaoCreme(CorCorpoCreme)
                        },
                        modifier = Modifier
                            .border(2.dp, Black, CircleShape)
                            .size(32.dp),
                        colors = ButtonDefaults.buttonColors(CorCorpoCreme)
                    ) {
                    }

                    Spacer(modifier = Modifier.width(24.dp))

                    Button(
                        onClick = {
                            aoClicarBotaoSunburst(CorCorpoSunburst)
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


//@Preview(showBackground = true)
//@Composable
//fun OpcoesDoInstrumentoMenuPreview() {
//    OpcoesDoInstrumentoMenu()
//}