package org.cazait.ui.viewmodel

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import org.cazait.ui.base.BaseViewModel
import org.cazait.ui.util.SingleEvent
import javax.inject.Inject

@HiltViewModel
class CafeInfoLocTransViewModel @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
) : BaseViewModel() {
    private val _userLocation = MutableLiveData<SingleEvent<Location>>()
    val userLocation: LiveData<SingleEvent<Location>>
        get() = _userLocation
}
