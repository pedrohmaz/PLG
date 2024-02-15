package com.plg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.plg.ui.screens.CustomizarInstrumentoScreen

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
          CustomizarInstrumentoScreen(this)

        }
    }
}

typealias function = () -> Unit






