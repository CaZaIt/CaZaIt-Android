package org.cazait.cazait_android.network

import android.net.ConnectivityManager

interface NetworkConnectivity {
    fun isConnected(): Boolean
    fun getConnectivityManager(): ConnectivityManager
}