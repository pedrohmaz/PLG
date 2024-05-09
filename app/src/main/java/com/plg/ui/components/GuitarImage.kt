package com.plg.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.viewModelFactory
import com.plg.R


@Composable
fun GuitarImage(
    body: Int,
    neck: Int,
    headstock: Int,
    shield: Int?,
    inlays: Int?,
    pieces: Int,
    bodyColor1: Color = Color.Black,
    bodyColor2: Color = Color.Black,
    neckColor: Color? = null,
    headstockColor: Color? = null,
    shieldColor: Color? = null,
    inlayColor: Color? = null,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier)
    {
        val bodyColorTotal = Brush.radialGradient(
            0.30f to bodyColor1,
            0.5f to bodyColor2,
            center = Offset(570f, 1200f),
            radius = Float.POSITIVE_INFINITY,
            tileMode = TileMode.Clamp
        )

// Use this Image Composable if the body consists of one plain color.
//        Image(
//            painter = painterResource(id = body),
//            contentDescription = null,
//            colorFilter = bodyColor1.let { ColorFilter.tint(it) },
//           // modifier = Modifier.background(corCorpoTotal)
//        )


// Use this Image Composable for complex colors, like gradient brushes.
        Image(
            painter = painterResource(body),
            contentDescription = null,
            modifier = Modifier
                .graphicsLayer {
                    compositingStrategy = CompositingStrategy.Offscreen
                }
                .drawWithContent {
                    drawContent()
                    drawRect(
                        brush = bodyColorTotal,
                        blendMode = BlendMode.SrcIn
                    )
                }
        )


        Image(
            shield?.let { painterResource(id = it) }
                ?: painterResource(id = R.drawable.transparente),
            contentDescription = null,
            colorFilter = shieldColor?.let { ColorFilter.tint(it) },

            )
        Image(
            painterResource(id = neck),
            contentDescription = null,
            colorFilter = neckColor?.let { ColorFilter.tint(it) },

            )
        Image(
            painterResource(id = headstock),
            contentDescription = null,
            colorFilter = headstockColor?.let { ColorFilter.tint(it) },
            )
        Image(
            inlays?.let { painterResource(id = it) }
                ?: painterResource(id = R.drawable.transparente),
            contentDescription = null,
            colorFilter = inlayColor?.let { ColorFilter.tint(it) },

            )
        Image(
            painterResource(id = pieces),
            contentDescription = null,

            )
    }
}

