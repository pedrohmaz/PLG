package com.plg.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.plg.R
import com.plg.function
import com.plg.ui.theme.Ice


enum class ButtonSelected {
    Body,
    Neck,
    Shield,
    Headstock,
    Inlays
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PartsMenu(
    onBodyButtonClick: function,
    onNeckButtonClick: function,
    onHeadstockButtonClick: function,
    onShieldButtonClick: function,
    onInlaysButtonClick: function,
    colorFunction: (ButtonSelected) -> Color
) {


    val espacamento = 25.dp
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Ice),

    )
    {
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = {
                onBodyButtonClick()
            }) {
            Icon(
                modifier = Modifier.scale(3f),
                tint = colorFunction(ButtonSelected.Body),
                painter = painterResource(id = R.drawable.icone_corpo),
                contentDescription = "Change body color"
            )
        }
        Spacer(modifier = Modifier.width(espacamento))
        IconButton(
            onClick = {
                onNeckButtonClick()
            }) {
            Icon(
                modifier = Modifier.scale(4.5f),
                tint = colorFunction(ButtonSelected.Neck),
                painter = painterResource(id = R.drawable.icone_braco),
                contentDescription = "Change neck color"
            )
        }
        Spacer(modifier = Modifier.width(espacamento))
        IconButton(
            onClick = {
                onHeadstockButtonClick()
            }) {
            Icon(
                modifier = Modifier.scale(3.5f),
                tint = colorFunction(ButtonSelected.Headstock),
                painter = painterResource(id = R.drawable.icone_headstock),
                contentDescription = "Change headstock color"
            )
        }
        Spacer(modifier = Modifier.width(espacamento))
        IconButton(
            onClick = {
                onShieldButtonClick()
            }) {
            Icon(
                modifier = Modifier.scale(3f),
                tint = colorFunction(ButtonSelected.Shield),
                painter = painterResource(id = R.drawable.icone_escudo),
                contentDescription = "Change shield color"
            )
        }
        Spacer(modifier = Modifier.width(espacamento))
        IconButton(
            onClick = {
                onInlaysButtonClick()
            }) {
            Icon(
                modifier = Modifier.scale(1.5f),
                tint = colorFunction(ButtonSelected.Inlays),
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Change inlays color"
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
    }
}




