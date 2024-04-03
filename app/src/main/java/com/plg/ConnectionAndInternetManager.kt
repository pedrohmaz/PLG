package com.plg

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun estaConectado(context: Context): Boolean{
    val gerenciadorDeConeccao = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capacitacoes = gerenciadorDeConeccao.getNetworkCapabilities(gerenciadorDeConeccao.activeNetwork) ?: return false
    return when {
        capacitacoes.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ->    true
        capacitacoes.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ->   true
        capacitacoes.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ->   true
        else ->     false
    }
}

