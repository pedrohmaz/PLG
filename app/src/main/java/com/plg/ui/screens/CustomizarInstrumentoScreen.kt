package com.plg.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plg.R
import com.plg.escolherFotoBraco
import com.plg.ui.components.GuitarraImagem
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
            val braco = remember { mutableStateOf(escolherFotoBraco()) }
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(16.dp))
                GuitarraImagem(corpo.value, braco.value)
                Spacer(modifier = Modifier.height(16.dp))
                OpcoesDoInstrumentoMenu(corpo, braco)

            }
        }
    }

}


