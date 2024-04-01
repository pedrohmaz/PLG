package com.plg.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    corCorpo: Color? = null,
    corBraco: Color? = null,
    corHeadstock: Color? = null,
    corEscudo: Color? = null,
    corMarcacoes: Color? = null,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier)
    {
        Image(
            painterResource(id = corpo),
            contentDescription = null,
            colorFilter = corCorpo?.let { ColorFilter.tint(it) },

        )
        Image(
            escudo?.let { painterResource(id = it) }
                ?: painterResource(id = R.drawable.transparente),
            contentDescription = null,
            colorFilter = corEscudo?.let { ColorFilter.tint(it) },

        )
        Image(
            painterResource(id = braco),
            contentDescription = null,
            colorFilter = corBraco?.let { ColorFilter.tint(it) },

        )
        Image(
            painterResource(id = headstock),
            contentDescription = null,
            colorFilter = corHeadstock?.let { ColorFilter.tint(it) },

        )
        Image(
            marcacoes?.let { painterResource(id = it) }
                ?: painterResource(id = R.drawable.transparente),
            contentDescription = null,
            colorFilter = corMarcacoes?.let { ColorFilter.tint(it) },

        )
        Image(
            painterResource(id = pecas),
            contentDescription = null,

        )
    }
}

