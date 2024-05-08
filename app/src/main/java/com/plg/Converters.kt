package com.plg

import android.icu.text.NumberFormat
import java.util.Locale

fun formatToReal(value: Float): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return formatter.format(value)
}