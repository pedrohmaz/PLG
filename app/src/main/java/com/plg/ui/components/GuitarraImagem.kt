package com.plg.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plg.R


@Composable
fun GuitarraImagem(corpo: Int, braco: Int) { 
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
                .align(Alignment.BottomCenter)
                .offset(y = (-8).dp)
        )
        Image(
            painter = painterResource(id = braco),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .scale(1.1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GuitarraPreview() {
    GuitarraImagem(
        corpo = R.drawable.corpo_sunburst_strato,
        braco = R.drawable.braco_escuro_strato
    )
}