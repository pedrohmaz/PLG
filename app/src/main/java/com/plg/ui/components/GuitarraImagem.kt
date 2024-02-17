package com.plg.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
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
    corHeadstock: ColorFilter? = null,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier)
    {
        Image(
            painterResource(id = corpo),
            contentDescription = null,
            colorFilter = corCorpo,

        )
        Image(
            escudo?.let { painterResource(id = it) }
                ?: painterResource(id = R.drawable.transparente),
            contentDescription = null,
            colorFilter = corEscudo,

        )
        Image(
            painterResource(id = braco),
            contentDescription = null,
            colorFilter = corBraco,

        )
        Image(
            painterResource(id = headstock),
            contentDescription = null,
            colorFilter = corHeadstock,

        )
        Image(
            marcacoes?.let { painterResource(id = it) }
                ?: painterResource(id = R.drawable.transparente),
            contentDescription = null,
            colorFilter = corMarcacoes,

        )
        Image(
            painterResource(id = pecas),
            contentDescription = null,

        )
    }
}

