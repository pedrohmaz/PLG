package com.plg.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
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
) {
    val sunburst = listOf(Orange, Black)
    Box(
        modifier = Modifier
            .height(85.dp)
            .fillMaxWidth()
            .background(Black)

    ) {
        Row(
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize().align(Center)
        ) {
            Button(
                onClick = {
                    aoClicarBotaoModelo()
                },
            ) {
                Icon(imageVector = Icons.Rounded.ArrowForward, contentDescription = "Trocar modelo")
            }

//            Spacer(modifier = Modifier.width(24.dp))

            Button(
                onClick = {
                    aoClicarBotaoVerde(CorCorpoVerde)
                },
                modifier = Modifier
                    .border(1.dp, White, CircleShape)
                    .size(40.dp),
                colors = ButtonDefaults.buttonColors(CorCorpoVerde)

            ) {
            }

//            Spacer(modifier = Modifier.width(24.dp))

            Button(
                onClick = {
                    aoClicarBotaoVermelho(CorCorpoVermelho)
                },
                modifier = Modifier
                    .border(1.dp, White, CircleShape)
                    .size(40.dp),
                colors = ButtonDefaults.buttonColors(CorCorpoVermelho)
            ) {
            }

//            Spacer(modifier = Modifier.width(24.dp))

            Button(
                onClick = {
                    aoClicarBotaoCreme(CorCorpoCreme)
                },
                modifier = Modifier
                    .border(1.dp, White, CircleShape)
                    .size(40.dp),
                colors = ButtonDefaults.buttonColors(CorCorpoCreme)
            ) {
            }

//            Spacer(modifier = Modifier.width(24.dp))

            Button(
                onClick = {
                    aoClicarBotaoSunburst(CorCorpoSunburst)
                },
                modifier = Modifier
                    .border(1.dp, White, CircleShape)
                    .background(Brush.horizontalGradient(sunburst), CircleShape)
                    .size(40.dp),
                colors = ButtonDefaults.buttonColors(Transparent)
            ) {
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun OpcoesDoInstrumentoMenuPreview() {
//    OpcoesDoInstrumentoMenu()
//}