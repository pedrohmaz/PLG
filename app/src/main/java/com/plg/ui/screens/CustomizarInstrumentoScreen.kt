package com.plg.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plg.R
import com.plg.ui.components.Guitarra
import com.plg.ui.components.OpcoesDoInstrumentoMenu
import com.plg.ui.theme.PLGTheme

@Composable
fun CustomizarInstrumentoScreen(){

    PLGTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val corpo = remember { mutableStateOf(R.drawable.corpo_sunburst_strato) }
            val braco = remember { mutableStateOf(R.drawable.braco_escuro_strato) }
            Column(
                // modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(16.dp))
                Guitarra(corpo.value, braco.value)
                Spacer(modifier = Modifier.height(16.dp))
                OpcoesDoInstrumentoMenu(corpo, braco)

            }
        }
    }

}


//fun onButtonClick(
//    state: MutableState<Int>,
//    corpo: MutableState<Int>,
//    braco: MutableState<Int>
//) {
//    state.value++
//    when (state.value) {
//        1 -> braco.value = R.drawable.braco_escuro_strato
//        2 -> corpo.value = R.drawable.corpo_vermelho_tele
//        3 -> braco.value = R.drawable.braco_claro_tele
//        4 -> {
//            corpo.value = R.drawable.corpo_sunburst_strato
//            state.value = 0
//        }
//
//        else -> state.value = 0
//    }
//}
