package com.plg

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun isConnected(context: Context): Boolean{
    val connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities = connectionManager.getNetworkCapabilities(connectionManager.activeNetwork) ?: return false
    return when {
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ->    true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ->   true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ->   true
        else ->     false
    }
}

