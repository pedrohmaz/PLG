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
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.plg.GuitarraBracos
import com.plg.GuitarraModelos
import com.plg.bracoGuit
import com.plg.escolherFotoBraco
import com.plg.escolherFotoCorpo
import com.plg.escolherFotoEscudo
import com.plg.escolherFotoHeadstock
import com.plg.escolherFotoMarcacoes
import com.plg.escolherFotoPecas
import com.plg.modeloGuit
import com.plg.ui.theme.CorCorpoCreme
import com.plg.ui.theme.CorCorpoSunburst
import com.plg.ui.theme.CorCorpoVerde
import com.plg.ui.theme.CorCorpoVermelho
import com.plg.ui.theme.CorEscalaClara
import com.plg.ui.theme.CorEscalaEscura
import com.plg.ui.theme.CorMarcacaoClara
import com.plg.ui.theme.CorMarcacaoEscura
import com.plg.ui.theme.Orange

@Composable
fun OpcoesDoInstrumentoMenu(
    corpo: MutableState<Int>,
    braco: MutableState<Int>,
    headstock: MutableState<Int>,
    escudo: MutableState<Int>,
    marcacoes: MutableState<Int>,
    pecas: MutableState<Int>,
    corCorpo: MutableState<ColorFilter>,
    corBraco: MutableState<ColorFilter>,
    corMarcacoes: MutableState<ColorFilter>

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
                        if (bracoGuit == GuitarraBracos.Escuro) {
                            corBraco.value = ColorFilter.tint(CorEscalaClara)
                            corMarcacoes.value = ColorFilter.tint(CorMarcacaoEscura)
                            bracoGuit = GuitarraBracos.Claro
                        } else {
                            corBraco.value = ColorFilter.tint(CorEscalaEscura)
                            corMarcacoes.value = ColorFilter.tint(CorMarcacaoClara)
                            bracoGuit = GuitarraBracos.Escuro
                        }
                    },
                    modifier = Modifier.align(CenterHorizontally)
                ) {
                    Text(text = "Mudar Escala")
                }
                Button(
                    onClick = {
                        modeloGuit = if (modeloGuit == GuitarraModelos.Strato) GuitarraModelos.Tele
                        else GuitarraModelos.Strato
                        aoClicarNoBotao(corpo, escolherFotoCorpo())
                        aoClicarNoBotao(braco, escolherFotoBraco())
                        aoClicarNoBotao(headstock, escolherFotoHeadstock())
                        aoClicarNoBotao(escudo, escolherFotoEscudo())
                        aoClicarNoBotao(marcacoes, escolherFotoMarcacoes())
                        aoClicarNoBotao(pecas, escolherFotoPecas())
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
                            corCorpo.value = ColorFilter.tint(CorCorpoVerde)
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
                            corCorpo.value = ColorFilter.tint(CorCorpoVermelho)
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
                            corCorpo.value = ColorFilter.tint(CorCorpoCreme)
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
                            corCorpo.value = ColorFilter.tint(CorCorpoSunburst)
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