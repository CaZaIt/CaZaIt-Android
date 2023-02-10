package org.cazait.cazait_android.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.sample.locationaddress.data.FormattedAddress
import com.google.android.gms.location.sample.locationaddress.data.GeocodingApi
import com.google.android.gms.location.sample.locationaddress.data.LocationApi
import com.google.android.gms.location.sample.locationaddress.data.PlayServicesAvailabilityChecker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    availabilityChecker: PlayServicesAvailabilityChecker,
    private val locationApi: LocationApi,
    private val geocodingApi: GeocodingApi
) : ViewModel() {

    val uiState = flow {
        emit(
            if (availabilityChecker.isGooglePlayServicesAvailable()) {
                UiState.PlayServicesAvailable
            } else {
                UiState.PlayServicesUnavailable
            }
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, UiState.Initializing)

    var addressList by mutableStateOf(emptyList<FormattedAddress>())
        private set

    var showProgress by mutableStateOf(false)
        private set

    val maxResultsRange = 1..7
    var maxResults by mutableStateOf(1)
        private set

    fun updateMaxResults(max: Int) {
        maxResults = max.coerceIn(maxResultsRange)
    }

    fun getCurrentAddress() {
        viewModelScope.launch {
            showProgress = true
            val location = locationApi.getCurrentLocation()
            val addresses = if (location != null) {
                geocodingApi.getFromLocation(location, maxResults)
            } else {
                emptyList()
            }
            addressList = addresses
            showProgress = false
        }
    }
}

enum class UiState {
    Initializing, PlayServicesUnavailable, PlayServicesAvailable
}
