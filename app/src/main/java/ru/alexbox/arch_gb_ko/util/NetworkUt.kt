package ru.alexbox.arch_gb_ko.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
// Deprecated
fun isOnline(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo : NetworkInfo?
    netInfo = connectivityManager.activeNetworkInfo
    return netInfo != null && netInfo.isConnected
}