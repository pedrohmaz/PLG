package com.plg.ui.viewmodels

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.plg.R
import com.plg.model.Guitar
import com.plg.model.remoteServer.RemoteDb
import com.plg.ui.components.ButtonSelected
import com.plg.ui.theme.RedBodyColor
import com.plg.ui.theme.DarkScaleColor
import com.plg.ui.theme.OriginalShieldColor
import com.plg.ui.theme.LightInlayColor
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class EditInstrumentViewModel(application: Application) : AndroidViewModel(application) {

    private val remoteDb = RemoteDb()

    private val _guitar = MutableStateFlow<Guitar?>(null)
    val guitar: StateFlow<Guitar?> get() = _guitar


    private val _body = MutableStateFlow(R.drawable.transparente)
    val body: StateFlow<Int> get() = _body
    private val _neck = MutableStateFlow(R.drawable.transparente)
    val neck: StateFlow<Int> get() = _neck
    private val _headstock = MutableStateFlow(R.drawable.transparente)
    val headstock: StateFlow<Int> get() = _headstock
    private val _shield = MutableStateFlow(R.drawable.transparente)
    val shield: StateFlow<Int> get() = _shield
    private val _inlays = MutableStateFlow(R.drawable.transparente)
    val inlays: StateFlow<Int> get() = _inlays
    private val _pieces = MutableStateFlow(R.drawable.transparente)
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

    private var guitModel = GuitarModels.Strato

    private enum class GuitarModels {
        Strato,
        Tele
    }

    private fun chooseNeckImage(): Int {
        return when (guitModel) {
            GuitarModels.Strato -> R.drawable.strato_braco
            GuitarModels.Tele -> R.drawable.tele_braco
        }
    }

    private fun chooseBodyImage(): Int {
        return when (guitModel) {
            GuitarModels.Strato -> R.drawable.strato_corpo
            GuitarModels.Tele -> R.drawable.tele_corpo
        }
    }

    private fun chooseHeadstockImage(): Int {
        return when (guitModel) {
            GuitarModels.Strato -> R.drawable.strato_headstock
            GuitarModels.Tele -> R.drawable.tele_headstock
        }
    }

    private fun chooseInlaysImage(): Int {
        return when (guitModel) {
            GuitarModels.Strato -> R.drawable.strato_marcacoes
            GuitarModels.Tele -> R.drawable.tele_marcacoes
        }
    }

    private fun chooseShieldImage(): Int {
        return when (guitModel) {
            GuitarModels.Strato -> R.drawable.strato_escudo
            GuitarModels.Tele -> R.drawable.tele_escudo
        }
    }

    private fun choosePiecesImage(): Int {
        return when (guitModel) {
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
        if (guitModel == GuitarModels.Strato) {
            guitModel = GuitarModels.Tele
            _modelValue.value = 1500f
            _totalValue.value = updateTotalValue()
        } else {
            guitModel = GuitarModels.Strato
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

    fun setInitialDrawing(
        body: Int, neck: Int, headstock: Int, shield: Int, inlays: Int,
        pieces: Int, bodyColor1: Int, bodyColor2: Int, neckColor: Int, headstockColor: Int,
        shieldColor: Int, inlaysColor: Int, valor: Float, modelValue: Float,
        scaleValue: Float, headstockValue: Float, shieldValue: Float
    ) {
        _body.value = body
        _neck.value = neck
        _headstock.value = headstock
        _shield.value = shield
        _inlays.value = inlays
        _pieces.value = pieces
        _bodyColor1.value = Color(bodyColor1)
        _bodyColor2.value = Color(bodyColor2)
        _neckColor.value = Color(neckColor)
        _headstockColor.value = Color(headstockColor)
        _shieldColor.value = Color(shieldColor)
        _inlaysColor.value = Color(inlaysColor)
        guitModel = if (_body.value == R.drawable.strato_corpo) {
            GuitarModels.Strato
        } else GuitarModels.Tele
        _totalValue.value = valor
        _modelValue.value = modelValue
        _scaleValue.value = scaleValue
        _headstockValue.value = headstockValue
        _shieldValue.value = shieldValue
        updateTotalValue()
    }

    fun getValue(value: StateFlow<Float>): Float {
        return value.value
    }

    suspend fun setGuitar(id: Long) {
        viewModelScope.async {
            remoteDb.getElementByParam("Guitars","id", id)?.addOnSuccessListener {
                _guitar.value = it.first().toObject()
            }?.await()
        }.await()
    }

}