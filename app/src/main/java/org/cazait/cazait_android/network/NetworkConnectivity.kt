package org.cazait.cazait_android.network

import android.net.NetworkInfo

interface NetworkConnectivity {
    fun getNetworkInfo(): NetworkInfo?
    fun isConnected(): Boolean
}