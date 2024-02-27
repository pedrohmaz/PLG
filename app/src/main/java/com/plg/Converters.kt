package com.plg

import android.icu.text.NumberFormat
import java.util.Locale

fun formatarParaReal(valor: Float): String {
    val formatador = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return formatador.format(valor)
}