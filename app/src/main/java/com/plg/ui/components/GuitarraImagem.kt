package com.plg.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plg.R


@Composable
fun GuitarraImagem(
    corpo: Int,
    braco: Int,
    headstock: Int,
    escudo: Int?,
    marcacoes: Int?,
    pecas: Int,
    corCorpo: ColorFilter? = null,
    corBraco: ColorFilter? = null,
    corMarcacoes: ColorFilter? = null,
    corEscudo: ColorFilter? = null,
    corHeadstock: ColorFilter? = null
) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .height(550.dp)
            .padding(vertical = 8.dp)

    ) {

        Image(
            painterResource(id = corpo),
            contentDescription = null,
            colorFilter = corCorpo,
            modifier = Modifier.scale(1.1f)
        )
        Image(
            escudo?.let { painterResource(id = it) }
                ?: painterResource(id = R.drawable.transparente),
            contentDescription = null,
            colorFilter = corEscudo,
            modifier = Modifier.scale(1.1f)
        )
        Image(
            painterResource(id = braco),
            contentDescription = null,
            colorFilter = corBraco,
            modifier = Modifier.scale(1.1f)
        )
        Image(
            painterResource(id = headstock),
            contentDescription = null,
            colorFilter = corHeadstock,
            modifier = Modifier.scale(1.1f)
        )
        Image(
            marcacoes?.let { painterResource(id = it) }
                ?: painterResource(id = R.drawable.transparente),
            contentDescription = null,
            colorFilter = corMarcacoes,
            modifier = Modifier.scale(1.1f)
        )
        Image(
            painterResource(id = pecas),
            contentDescription = null,
            modifier = Modifier.scale(1.1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GuitarraPreview() {
    GuitarraImagem(
        corpo = R.drawable.corpo_sunburst_strato,
        braco = R.drawable.braco_escuro_strato,
        headstock = R.drawable.strato_headstock,
        escudo = R.drawable.strato_escudo,
        marcacoes = R.drawable.strato_marcacoes,
        pecas = R.drawable.strato_pecas
    )
}