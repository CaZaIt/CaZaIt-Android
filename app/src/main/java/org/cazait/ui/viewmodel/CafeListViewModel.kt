package org.cazait.ui.viewmodel

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.cazait.data.Resource
import org.cazait.data.error.EXPIRED_ACCESS_TOKEN
import org.cazait.data.model.Cafe
import org.cazait.data.model.remote.request.CafeListRequest
import org.cazait.data.model.remote.response.CafeListResponse
import org.cazait.data.repository.DataRepository
import org.cazait.data.repository.UserRepository
import org.cazait.ui.base.BaseViewModel
import org.cazait.ui.util.SingleEvent
import javax.inject.Inject

@HiltViewModel
open class CafeListViewModel @Inject constructor(
    private val dataRepository: DataRepository,
    private val userRepository: UserRepository,
    private val fusedLocationProviderClient: FusedLocationProviderClient,
) : BaseViewModel() {
    private val _userIdLiveData = MutableLiveData<Long>()
    val userIdLiveData: LiveData<Long>
        get() = _userIdLiveData

    private val _cafesLiveData = MutableLiveData<Resource<CafeListResponse>>()
    val cafesLiveData: LiveData<Resource<CafeListResponse>>
        get() = _cafesLiveData

    private val _openCafeDetails = MutableLiveData<SingleEvent<Cafe>>()
    val openCafeDetails: LiveData<SingleEvent<Cafe>>
        get() = _openCafeDetails

    private val _showToast = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>>
        get() = _showToast

    private val _userLocation = MutableLiveData<SingleEvent<Location>>()
    val userLocation: LiveData<SingleEvent<Location>>
        get() = _userLocation

    /**
     * 카페 목록을 새로 고침 한다.
     * 즉, user의 위치에 기반하여 새로운 request를 보낸다.
     */
    fun refreshCafeList() {
        viewModelScope.launch {
            _userIdLiveData.value = userRepository.fetchUserIdInDataStore().first()
            val testLongitude = "126.9457"
            val testLatitude = "37.586"
            val testLimit = "0"
            val testSort = "distance"

            val request: CafeListRequest = if (userLocation.value != null) {
                CafeListRequest(
                    userLocation.value!!.peekContent().latitude.toString(),
                    userLocation.value!!.peekContent().longitude.toString(),
                    "0",
                    testSort,
                )
            } else {
                CafeListRequest(testLatitude, testLongitude, testLimit, testSort)
            }

            Log.d("CafeListViewModel", "선택된 것은... ${request.latitude}, ${request.longitude}")
            viewModelScope.launch {
                _cafesLiveData.value = Resource.Loading()
                dataRepository.getCafes(_userIdLiveData.value!!, query = request).collect {
                    if (isExpiredToken(it)) {
                        Log.d("CafeListViewModel", "refreshTokens")
                        refreshTokens()
                    }
                    _cafesLiveData.value = it
                }
            }
        }
    }

    fun openCafeDetails(cafe: Cafe) {
        _openCafeDetails.value = SingleEvent(cafe)
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        _showToast.value = SingleEvent(error.description)
    }

    fun showToastMessage(errorMessage: String?) {
        if (errorMessage == null) return
        _showToast.value = SingleEvent(errorMessage)
    }

    /**
     * 현재 user의 위치 값을 알아낸다.
     */
    @SuppressLint("MissingPermission")
    fun getUserLocation() {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                Log.d("MyFragmentViewModel", "Latitude: ${location.latitude}")
                Log.d("MyFragmentViewModel", "Longitude: ${location.longitude}")
                _userLocation.value = SingleEvent(location)
            }
        }
    }

    fun likeCafe(userId: Long, cafeId: Long) {
        viewModelScope.launch {
            dataRepository.postInterestCafe(userId = userId, cafeId = cafeId).collect {
            }
        }
    }

    fun dislikeCafe(favoritesId: Long) {
        viewModelScope.launch {
            dataRepository.deleteInterestCafe(favoritesId).collect()
        }
    }

    private fun isExpiredToken(response: Resource<CafeListResponse>): Boolean {
        if (response is Resource.Success &&
            response.data.result == "FAIL" &&
            response.data.message == errorManager.getError(EXPIRED_ACCESS_TOKEN).description
        ) {
            return true
        }
        return false
    }

    private suspend fun refreshTokens() {
        val tokenResponse = userRepository.postToken().first()
        if (tokenResponse is Resource.Success) {
            userRepository.saveToken(
                listOf(
                    tokenResponse.data.data.jwtToken,
                    tokenResponse.data.data.refreshToken,
                ),
            )
        }
    }
}
