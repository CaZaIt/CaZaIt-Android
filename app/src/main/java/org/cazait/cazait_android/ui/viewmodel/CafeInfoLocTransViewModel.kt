package org.cazait.cazait_android.ui.viewmodel

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import org.cazait.cazait_android.ui.base.BaseViewModel
import org.cazait.cazait_android.ui.util.SingleEvent
import javax.inject.Inject

class CafeInfoLocTransViewModel @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : BaseViewModel() {
    private val _userLocation = MutableLiveData<SingleEvent<Location>>()
    val userLocation: LiveData<SingleEvent<Location>>
        get() = _userLocation


}