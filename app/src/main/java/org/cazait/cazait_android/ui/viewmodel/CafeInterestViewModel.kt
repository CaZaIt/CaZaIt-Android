package org.cazait.cazait_android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.error.EXPIRED_ACCESS_TOKEN
import org.cazait.cazait_android.data.model.Cafe
import org.cazait.cazait_android.data.model.remote.response.InterestCafesResponse
import org.cazait.cazait_android.data.repository.DataRepository
import org.cazait.cazait_android.data.repository.UserRepository
import org.cazait.cazait_android.ui.base.BaseViewModel
import org.cazait.cazait_android.ui.util.SingleEvent
import javax.inject.Inject

@HiltViewModel
open class CafeInterestViewModel @Inject constructor(
    private val dataRepository: DataRepository,
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _showToast = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>>
        get() = _showToast

    private val _interestCafes = MutableLiveData<Resource<InterestCafesResponse>>()
    val interestCafes: LiveData<Resource<InterestCafesResponse>>
        get() = _interestCafes

    private val _openCafeDetails = MutableLiveData<SingleEvent<Cafe>>()
    val openCafeDetails: LiveData<SingleEvent<Cafe>>
        get() = _openCafeDetails

    fun refreshInterestCafeList() {
        viewModelScope.launch {
            val userId = userRepository.fetchUserIdInDataStore().first()

            _interestCafes.value = Resource.Loading()
            dataRepository.getInterestCafes(userId).collect {
                if (isExpiredToken(it)) {
                    refreshTokens()
                }
                _interestCafes.value = it
            }
        }
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        _showToast.value = SingleEvent(error.description)
    }

    fun showToastMessage(errorMessage: String?) {
        if (errorMessage == null) return
        _showToast.value = SingleEvent(errorMessage)
    }

    fun openCafeDetails(cafe: Cafe) {
        _openCafeDetails.value = SingleEvent(cafe)
    }

    private fun isExpiredToken(response: Resource<InterestCafesResponse>): Boolean {
        return response is Resource.Success
                && response.data.result == "FAIL"
                && response.data.message == errorManager.getError(EXPIRED_ACCESS_TOKEN).description
    }

    private suspend fun refreshTokens() {
        val refreshToken = userRepository.fetchTokenInDataStore().first()

        val tokenResponse =
            userRepository.postToken(mapOf("REFRESH-TOKEN" to refreshToken.last())).first()
        if (tokenResponse is Resource.Success) userRepository.saveToken(
            listOf(
                tokenResponse.data.data.jwtToken,
                tokenResponse.data.data.refreshToken
            )
        )
    }
}