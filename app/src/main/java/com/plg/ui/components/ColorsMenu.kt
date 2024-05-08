package com.plg.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.plg.function
import com.plg.ui.theme.BlueBodyColor
import com.plg.ui.theme.CreamBodyColor
import com.plg.ui.theme.SunburstBodyColor
import com.plg.ui.theme.GreenBodyColor
import com.plg.ui.theme.RedBodyColor
import com.plg.ui.theme.LightScaleColor
import com.plg.ui.theme.DarkScaleColor
import com.plg.ui.theme.LightInlayColor
import com.plg.ui.theme.DarkInlayColor
import com.plg.ui.theme.OriginalRedColor


@Composable
fun ColorsMenuMutable(
    onButton1Click: function,
    button1Color: Color,
    onButton2Click: function,
    button2Color: Color,
    button3Active: Boolean = false,
    onButton3Click: function = {},
    button3Color: Color = Transparent,
    button4Active: Boolean = false,
    onButton4Click: function = {},
    button4Color: Color = Transparent,
    button5Active: Boolean = false,
    onButton5Click: function = {},
    button5Color: Color = Transparent,
    button6Active: Boolean = false,
    onButton6Click: function = {},
    button6Color: Brush = Brush.horizontalGradient(listOf(Transparent, Transparent))
) {


    Row(
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(Black)
    ) {
        Button(
            onClick = {
                onButton1Click()
            },
            modifier = Modifier
                .border(1.dp, White, CircleShape)
                .background(button1Color, CircleShape)
                .size(40.dp),
            colors = ButtonDefaults.buttonColors(Transparent)

        ) {
        }

        Button(
            onClick = {
                onButton2Click()
            },
            modifier = Modifier
                .border(1.dp, White, CircleShape)
                .background(button2Color, CircleShape)
                .size(40.dp),
            colors = ButtonDefaults.buttonColors(Transparent)

        ) {
        }

        Button(
            onClick = {
                onButton3Click()
            },
            modifier = Modifier
                .alpha(if (button3Active) 1f else 0f)
                .border(1.dp, White, CircleShape)
                .background(button3Color, CircleShape)
                .size(40.dp),
            colors = ButtonDefaults.buttonColors(Transparent)
        ) {
        }

        Button(
            onClick = {
                onButton4Click()
            },
            modifier = Modifier
                .alpha(if (button4Active) 1f else 0f)
                .border(1.dp, White, CircleShape)
                .background(button4Color, CircleShape)
                .size(40.dp),
            colors = ButtonDefaults.buttonColors(Transparent),
        ) {
        }

        Button(
            onClick = {
                onButton5Click()
            },
            modifier = Modifier
                .alpha(if (button5Active) 1f else 0f)
                .border(1.dp, White, CircleShape)
                .background(button5Color, CircleShape)
                .size(40.dp),
            colors = ButtonDefaults.buttonColors(Transparent)
        ) {
        }

        Button(
            onClick = {
                onButton6Click()
            },
            modifier = Modifier
                .alpha(if (button6Active) 1f else 0f)
                .border(1.dp, White, CircleShape)
                .background(button6Color, CircleShape)
                .size(40.dp),
            colors = ButtonDefaults.buttonColors(Transparent)
        ) {
        }
    }
}


@Composable
fun ColorsMenuBody(selectedPart: (Color, Color, valor: Float) -> Unit) {
    ColorsMenuMutable(
        onButton1Click = { selectedPart(RedBodyColor, RedBodyColor,  0f) },
        button1Color = RedBodyColor,
        onButton2Click = { selectedPart(GreenBodyColor, GreenBodyColor, 0f) },
        button2Color = GreenBodyColor,
        button3Active = true,
        onButton3Click = { selectedPart(CreamBodyColor, CreamBodyColor, 0f) },
        button3Color = CreamBodyColor,
        button4Active = true,
        onButton4Click = { selectedPart(Black, Black, 0f) },
        button4Color = Black,
        button5Active = true,
        onButton5Click = { selectedPart(BlueBodyColor, BlueBodyColor, 0f) },
        button5Color = BlueBodyColor,
        button6Active = true,
        onButton6Click = { selectedPart(OriginalRedColor, Black, 0f) },
        button6Color = Brush.horizontalGradient(listOf(OriginalRedColor, Black))
    )
}

@Composable
fun ColorsMenuNeck(selectedPart: (Color, Color, valor: Float) -> Unit) {
    ColorsMenuMutable(
        onButton1Click = { selectedPart(DarkScaleColor, DarkScaleColor, 100f) },
        button1Color = DarkScaleColor,
        onButton2Click = { selectedPart(LightScaleColor, LightScaleColor, 0f) },
        button2Color = LightScaleColor,
    )
}

@Composable
fun ColorsMenuHeadstock(selectedPart: (Color, Color, valor: Float) -> Unit ) {
    ColorsMenuMutable(
        onButton1Click = {
            selectedPart(RedBodyColor, RedBodyColor, 150f)
        },
        button1Color = RedBodyColor,
        onButton2Click = { selectedPart(GreenBodyColor, GreenBodyColor, 150f) },
        button2Color = GreenBodyColor,
        button3Active = true,
        onButton3Click = { selectedPart(CreamBodyColor, CreamBodyColor, 150f) },
        button3Color = CreamBodyColor,
        button4Active = true,
        onButton4Click = { selectedPart(Black, Black,150f) },
        button4Color = Black,
        button5Active = true,
        onButton5Click = { selectedPart(BlueBodyColor, BlueBodyColor, 150f) },
        button5Color = BlueBodyColor,
        button6Active = true,
        onButton6Click = { selectedPart(LightScaleColor, LightScaleColor, 0f) },
        button6Color = Brush.horizontalGradient(listOf(LightScaleColor, LightScaleColor))
    )
}

@Composable
fun ColorsMenuShield(selectedPart: (Color, Color, valor: Float) -> Unit) {
    ColorsMenuMutable(
        onButton1Click = { selectedPart(RedBodyColor, RedBodyColor, 200f) },
        button1Color = RedBodyColor,
        onButton2Click = { selectedPart(GreenBodyColor, GreenBodyColor, 200f) },
        button2Color = GreenBodyColor,
        button3Active = true,
        onButton3Click = { selectedPart(CreamBodyColor, CreamBodyColor, 200f) },
        button3Color = CreamBodyColor,
        button4Active = true,
        onButton4Click = { selectedPart(Black, Black,200f) },
        button4Color = Black,
        button5Active = true,
        onButton5Click = { selectedPart(BlueBodyColor, BlueBodyColor, 200f) },
        button5Color = BlueBodyColor,
        button6Active = true,
        onButton6Click = { selectedPart(Transparent, Transparent, 0f ) },
        button6Color = Brush.horizontalGradient(listOf(Black, White, Black, White, Black, White, Black, White, Black, White, Black, White))
    )
}

@Composable
fun ColorsMenuInlays(selectedPart: (Color, Color, valor: Float) -> Unit) {
    ColorsMenuMutable(
        onButton1Click = { selectedPart(DarkInlayColor, DarkInlayColor, 0f) },
        button1Color = DarkInlayColor,
        onButton2Click = { selectedPart(LightInlayColor, LightInlayColor, 0f)},
        button2Color = LightInlayColor,
        button3Active = true,
        onButton3Click = { selectedPart(SunburstBodyColor, SunburstBodyColor, 0f) },
        button3Color = SunburstBodyColor,
    )
}


//@Preview(showBackground = true)
//@Composable
//fun OpcoesDoInstrumentoMenuPreview() {
//    OpcoesDoInstrumentoMenu()
//}