package com.plg.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
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
            0.6f to bodyColor2,
            center = Offset(550f, 1100f),
            radius = Float.POSITIVE_INFINITY,
            tileMode = TileMode.Clamp
        )


        fun motherOfPearlBrush(): Brush {
            val colors = arrayOf(
                0f to Color(0xFFE3FFB6) , // Light green
               0.15f to Color(0xFFA3FFA3), // Lighter green
                0.40f to Color(0xFF72CED6), // Light blue
               0.55f to Color(0xFFAEE5D8), // Lighter blue
               0.70f to Color(0xFFE6D7F4), // Light purple
               0.95f to Color(0xFFFAE3CB), // Light orange
            )
            return linearGradient(
                colorStops = colors,
                start = Offset.Zero,
                end = Offset(30f, 30f),
                tileMode = TileMode.Mirror
            )
        }

        Image(
            painter = painterResource(id = body),
            contentDescription = null,
            colorFilter = bodyColor1.let { ColorFilter.tint(it) },
           // modifier = Modifier.background(corCorpoTotal)
        )

// Usar esta Image para cores complexas no corpo

//        Image(
//            painter = painterResource(corpo),
//            contentDescription = null,
//            modifier = Modifier
//          //       .scale(0.7f)
////                .offset(x = 52.dp, y = 71.dp)
//                //.alpha(1f)
//                .drawWithContent {
//                    drawContent()
//                    drawRect(
//                        brush = SolidColor(Red),
//                        blendMode = BlendMode.Modulate
//                    )
//                }
//        )


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

