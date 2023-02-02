package org.cazait.cazait_android.network

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class Network @Inject constructor(val context: Context) : NetworkConnectivity {
    override fun getConnectivityManager(): ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun isConnected(): Boolean {
        return getConnectivityManager().isDefaultNetworkActive
    }
}