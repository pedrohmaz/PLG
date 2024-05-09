package com.plg.ui.screens

import android.content.res.Configuration
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.sharp.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plg.formatToReal
import com.plg.function
import com.plg.ui.components.ButtonSelected
import com.plg.ui.components.GuitarImage
import com.plg.ui.components.ColorsMenuNeck
import com.plg.ui.components.ColorsMenuBody
import com.plg.ui.components.ColorsMenuShield
import com.plg.ui.components.ColorsMenuHeadstock
import com.plg.ui.components.ColorsMenuInlays
import com.plg.ui.components.PartsMenu
import com.plg.ui.theme.PLGTheme
import com.plg.ui.viewmodels.EditInstrumentViewModel
import com.plg.ui.viewmodels.GlobalViewModel


@Composable
fun EditInstrumentScreen(
    activity: ComponentActivity,
    onNavigateToSaveInstrument: (Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Float, Float, Float, Float, Float, String, String) -> Unit
) {
    val globalViewModel: GlobalViewModel by activity.viewModels()
    val viewModel: EditInstrumentViewModel by activity.viewModels()
    val userId = globalViewModel.userId.collectAsState()
    val guitarId by globalViewModel.guitarId.collectAsState()
    val selectedPart = remember { viewModel.changeSelectedPart() }
    val colorMenu: MutableState<@Composable function> =
        remember { mutableStateOf(@Composable { ColorsMenuBody(selectedPart) }) }
    val selectedButton = viewModel.buttonSelected.collectAsState()
    val guitar by viewModel.guitar.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.setGuitar(guitarId)
        guitar?.let { guitarra ->
            viewModel.setInitialDrawing(
                guitarra.body,
                guitarra.neck,
                guitarra.headstock,
                guitarra.shield,
                guitarra.inlays,
                guitarra.pieces,
                guitarra.bodyColor1,
                guitarra.bodyColor2,
                guitarra.neckColor,
                guitarra.headstockColor,
                guitarra.shieldColor,
                guitarra.inlayColor,
                guitarra.value,
                guitarra.modelValue,
                guitarra.scaleValue,
                guitarra.headstockValue,
                guitarra.shieldValue

            )
        }
    }


    fun changeColorsMenu() {
        return when (selectedButton.value) {
            ButtonSelected.Body -> colorMenu.value =
                { ColorsMenuBody(viewModel.changeSelectedPart()) }

            ButtonSelected.Neck -> colorMenu.value =
                { ColorsMenuNeck(viewModel.changeSelectedPart()) }

            ButtonSelected.Headstock -> colorMenu.value =
                { ColorsMenuHeadstock(viewModel.changeSelectedPart()) }

            ButtonSelected.Shield -> colorMenu.value =
                { ColorsMenuShield(viewModel.changeSelectedPart()) }

            ButtonSelected.Inlays -> colorMenu.value =
                { ColorsMenuInlays(viewModel.changeSelectedPart()) }
        }
    }

    val body = viewModel.body.collectAsState()
    val neck = viewModel.neck.collectAsState()
    val headstock = viewModel.headstock.collectAsState()
    val shield = viewModel.shield.collectAsState()
    val inlays = viewModel.inlays.collectAsState()
    val pieces = viewModel.pieces.collectAsState()
    val bodyColor1 = viewModel.bodyColor1.collectAsState()
    val bodyColor2 = viewModel.bodyColor2.collectAsState()
    val neckColor = viewModel.neckColor.collectAsState()
    val headstockColor = viewModel.headstockColor.collectAsState()
    val inlaysColor = viewModel.inlaysColor.collectAsState()
    val shieldCOlor = viewModel.shieldColor.collectAsState()
    val value by viewModel.totalValue.collectAsState()

    val configuration = LocalConfiguration.current
    val isHorizontal = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE


    fun chooseButtonColor(button: ButtonSelected): Color {
        return if (selectedButton.value == button) {
            Color.Red
        } else {
            Color.Black
        }
    }

    var scale by remember { mutableFloatStateOf(1f) }
    // var rotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, offsetChange, _ ->
        scale *= zoomChange
        // rotation += rotationChange
        offset += offsetChange
    }

    PLGTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                GuitarImage(
                    body.value,
                    neck.value,
                    headstock.value,
                    shield.value,
                    inlays.value,
                    pieces.value,
                    bodyColor1.value,
                    bodyColor2.value,
                    neckColor.value,
                    headstockColor.value,
                    shieldCOlor.value,
                    inlaysColor.value,
                    modifier = Modifier
                        .scale(if (isHorizontal) 1.5f else 1.1f)
                        .rotate(
                            if (isHorizontal) 90f
                            else 0f
                        )
                        .align(if (isHorizontal) Alignment.Center else Alignment.TopCenter)
                        .offset(
                            x = if (isHorizontal) (-40).dp else 0.dp,
                            y = if (isHorizontal) 0.dp else 50.dp
                        )
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale,
                            // rotationZ = rotation,
                            translationX = offset.x,
                            translationY = offset.y
                        )
                        .transformable(state = state)
                    //.background(Blue)
                )
                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp),
                    onClick = {
                        globalViewModel.setUpdatingGuitar(true)
                        onNavigateToSaveInstrument(
                            body.value,
                            neck.value,
                            headstock.value,
                            shield.value,
                            inlays.value,
                            pieces.value,
                            bodyColor1.value.toArgb(),
                            bodyColor2.value.toArgb(),
                            neckColor.value.toArgb(),
                            headstockColor.value.toArgb(),
                            shieldCOlor.value.toArgb(),
                            inlaysColor.value.toArgb(),
                            value,
                            viewModel.getValue(viewModel.modelValue),
                            viewModel.getValue(viewModel.scaleValue),
                            viewModel.getValue(viewModel.headstockValue),
                            viewModel.getValue(viewModel.shieldValue),
                            guitar!!.name,
                            userId.value
                        )
                    }) {
                    Row {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            modifier = Modifier.align(Alignment.CenterVertically),
                            text = " ${formatToReal(value)} ",
                            fontSize = 16.sp
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Botão valor / ir para tela 'salvar pedido'"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
                FloatingActionButton(
                    modifier = Modifier
                        .align(if (isHorizontal) Alignment.CenterEnd else Alignment.BottomEnd)
                        .offset(
                            if (isHorizontal) (-16).dp else (-16).dp,
                            if (isHorizontal) 4.dp else (-140).dp
                        ),
                    onClick = { viewModel.changeModel() }) {
                    Icon(
                        imageVector = Icons.Sharp.Refresh,
                        contentDescription = "Icone Seta"
                    )
                }
                Column(
                    Modifier.align(Alignment.BottomCenter)
                ) {
                    changeColorsMenu()
                    colorMenu.value()

                    PartsMenu(
                        onBodyButtonClick = {
                            viewModel.changeSelectedButton(ButtonSelected.Body)
                        },
                        onNeckButtonClick = {
                            viewModel.changeSelectedButton(ButtonSelected.Neck)
                        },
                        onHeadstockButtonClick = {
                            viewModel.changeSelectedButton(ButtonSelected.Headstock)
                        },
                        onShieldButtonClick = {
                            viewModel.changeSelectedButton(ButtonSelected.Shield)
                        },
                        onInlaysButtonClick = {
                            viewModel.changeSelectedButton(ButtonSelected.Inlays)
                        },
                        colorFunction = ::chooseButtonColor
                    )
                }
            }
        }
    }
}
