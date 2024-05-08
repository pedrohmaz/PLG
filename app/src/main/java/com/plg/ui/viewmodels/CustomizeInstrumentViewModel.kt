package com.plg.ui.viewmodels

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.plg.R
import com.plg.ui.components.ButtonSelected
import com.plg.ui.theme.RedBodyColor
import com.plg.ui.theme.DarkScaleColor
import com.plg.ui.theme.OriginalShieldColor
import com.plg.ui.theme.LightInlayColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CustomizeInstrumentViewModel : ViewModel() {


    private val _body = MutableStateFlow(R.drawable.stratocorpopngwhite2)
    val body: StateFlow<Int> get() = _body
    private val _neck = MutableStateFlow(R.drawable.strato_braco)
    val neck: StateFlow<Int> get() = _neck
    private val _headstock = MutableStateFlow(R.drawable.strato_headstock)
    val headstock: StateFlow<Int> get() = _headstock
    private val _shield = MutableStateFlow(R.drawable.strato_escudo)
    val shield: StateFlow<Int> get() = _shield
    private val _inlays = MutableStateFlow(R.drawable.strato_marcacoes)
    val inlays: StateFlow<Int> get() = _inlays
    private val _pieces = MutableStateFlow(R.drawable.strato_pecas)
    val pieces: StateFlow<Int> get() = _pieces
    private val _bodyColor1 = MutableStateFlow(RedBodyColor)
    val bodyColor1: StateFlow<Color> get() = _bodyColor1
    private val _bodyColor2 = MutableStateFlow(RedBodyColor)
    val bodyColor2: StateFlow<Color> get() = _bodyColor2
    private val _neckColor = MutableStateFlow(DarkScaleColor)
    val neckColor: StateFlow<Color> get() = _neckColor
    private val _headstockColor = MutableStateFlow(LightInlayColor)
    val headstockColor: StateFlow<Color> get() = _headstockColor
    private val _shieldColor = MutableStateFlow(OriginalShieldColor)
    val shieldColor: StateFlow<Color> get() = _shieldColor
    private val _inlaysColor = MutableStateFlow(LightInlayColor)
    val inlaysColor: StateFlow<Color> get() = _inlaysColor
    private val _buttonSelected = MutableStateFlow(ButtonSelected.Body)
    val buttonSelected: StateFlow<ButtonSelected> get() = _buttonSelected

    private val _modelValue = MutableStateFlow(2000f)
    val modelValue: StateFlow<Float> get() = _modelValue
    private val _scaleValue = MutableStateFlow(0f)
    val scaleValue: StateFlow<Float> get() = _scaleValue
    private val _headstockValue = MutableStateFlow(100f)
    val headstockValue: StateFlow<Float> get() = _headstockValue
    private val _shieldValue = MutableStateFlow(200f)
    val shieldValue: StateFlow<Float> get() = _shieldValue
    private val _totalValue = MutableStateFlow(updateTotalValue())
    val totalValue: StateFlow<Float> get() = _totalValue

    private var guitarModel = GuitarModels.Strato

    private enum class GuitarModels {
        Strato,
        Tele
    }

    private fun chooseNeckImage(): Int {
        return when (guitarModel) {
            GuitarModels.Strato -> R.drawable.strato_braco
            GuitarModels.Tele -> R.drawable.tele_braco
        }
    }

    private fun chooseBodyImage(): Int {
        return when (guitarModel) {
            GuitarModels.Strato -> R.drawable.strato_corpo
            GuitarModels.Tele -> R.drawable.tele_corpo
        }
    }

    private fun chooseHeadstockImage(): Int {
        return when (guitarModel) {
            GuitarModels.Strato -> R.drawable.strato_headstock
            GuitarModels.Tele -> R.drawable.tele_headstock
        }
    }

    private fun chooseInlaysImage(): Int {
        return when (guitarModel) {
            GuitarModels.Strato -> R.drawable.strato_marcacoes
            GuitarModels.Tele -> R.drawable.tele_marcacoes
        }
    }

    private fun chooseShieldImage(): Int {
        return when (guitarModel) {
            GuitarModels.Strato -> R.drawable.strato_escudo
            GuitarModels.Tele -> R.drawable.tele_escudo
        }
    }

    private fun choosePiecesImage(): Int {
        return when (guitarModel) {
            GuitarModels.Strato -> R.drawable.strato_pecas
            GuitarModels.Tele -> R.drawable.tele_pecas
        }
    }


    private fun changeBodyColor(color: Color, color2: Color, value: Float) {
        _bodyColor1.value = color
        _bodyColor2.value = color2
    }

    private fun changeShieldColor(color: Color, color2: Color, value: Float) {
        _shieldColor.value = color
        _shieldValue.value = value
        _totalValue.value = updateTotalValue()
    }

    private fun changeHeadstockColor(color: Color, color2: Color, value: Float) {
        _headstockColor.value = color
        _headstockValue.value = value
        _totalValue.value = updateTotalValue()
    }

    private fun changeNeckColor(color: Color, color2: Color, value: Float) {
        _neckColor.value = color
        _scaleValue.value = value
        _totalValue.value = updateTotalValue()
    }

    private fun changeInlaysColor(color: Color, color2: Color, value: Float) {
        _inlaysColor.value = color
    }

    fun changeModel() {
        if (guitarModel == GuitarModels.Strato) {
            guitarModel = GuitarModels.Tele
            _modelValue.value = 1500f
            _totalValue.value = updateTotalValue()
        } else {
            guitarModel = GuitarModels.Strato
            _modelValue.value = 2000f
            _totalValue.value = updateTotalValue()
        }
        _body.value = chooseBodyImage()
        _neck.value = chooseNeckImage()
        _headstock.value = chooseHeadstockImage()
        _shield.value = chooseShieldImage()
        _inlays.value = chooseInlaysImage()
        _pieces.value = choosePiecesImage()
    }

    private fun updateTotalValue(): Float {
        return _modelValue.value + _headstockValue.value + _scaleValue.value + _shieldValue.value
    }

    fun changeSelectedButton(button: ButtonSelected) {
        _buttonSelected.value = button
    }

    fun changeSelectedPart(): (Color, Color, Float) -> Unit {
        return when (_buttonSelected.value) {
            ButtonSelected.Body -> ::changeBodyColor
            ButtonSelected.Neck -> ::changeNeckColor
            ButtonSelected.Headstock -> ::changeHeadstockColor
            ButtonSelected.Shield -> ::changeShieldColor
            ButtonSelected.Inlays -> ::changeInlaysColor
        }
    }

    fun getValue(value: StateFlow<Float>) : Float{
        return value.value
    }

}