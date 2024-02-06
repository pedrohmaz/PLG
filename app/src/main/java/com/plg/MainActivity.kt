package com.plg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plg.ui.theme.PLGTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var state = 1

        setContent {
            PLGTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var corpo by remember { mutableStateOf(R.drawable.corpo_sunburst_strato) }
                    var braco by remember { mutableStateOf(R.drawable.braco_escuro_strato) }
                    Column(
                        // modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = CenterHorizontally
                    ) {

                        Guitar(corpo, braco)
                        Button(
                            onClick = {
                                state++
                                when (state) {
                                    1 -> braco = R.drawable.braco_escuro_strato
                                    2 -> corpo = R.drawable.corpo_vermelho_tele
                                    3 -> braco = R.drawable.braco_claro_tele
                                    4 -> {
                                        corpo = R.drawable.corpo_sunburst_strato
                                        state = 0
                                    } else -> state = 0
                                }

                            },
                            modifier = Modifier.align(CenterHorizontally)
                        ) {
                            Text("Trocar")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Guitar(corpo: Int, braco: Int) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .height(500.dp)
    ) {
        Image(
            painter = painterResource(id = corpo),
            contentDescription = null,
            modifier = Modifier
                .scale(1.1f)
                .align(BottomCenter)
                .offset(y = (-6).dp)

        )
        Image(
            painter = painterResource(id = braco),
            contentDescription = null,
            modifier = Modifier
                .align(TopCenter)
                .scale(1.1f)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PLGTheme {

    }
}