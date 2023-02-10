package org.cazait.cazait_android.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.remote.request.LoginRequest
import org.cazait.cazait_android.data.model.remote.response.LoginResponse
import org.cazait.cazait_android.data.repository.UserRepository
import org.cazait.cazait_android.ui.base.BaseViewModel
import org.cazait.cazait_android.ui.util.SingleEvent
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    @ApplicationContext context: Context,
) : BaseViewModel() {

    private val _loginProcess = MutableLiveData<Resource<LoginResponse>>()
    val loginProcess: LiveData<Resource<LoginResponse>>
        get() = _loginProcess

    private val _showToast = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>>
        get() = _showToast

    fun doLogin(email: String, password: String) {
        viewModelScope.launch {
            _loginProcess.value = Resource.Loading()
            userRepository.login(body = LoginRequest(email, password)).collect {
                if (it is Resource.Success && it.data.result == "SUCCESS") {
                    saveLoginToken(it.data.data.jwtToken, it.data.data.refreshToken)
                    saveUserId(it.data.data.id)
                    saveLoginEmail(email)
                }
                _loginProcess.value = it
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

    suspend fun isLoggedIn() = userRepository.isLoggedIn()

    private fun clearUserPreferences() {
        viewModelScope.launch {
            userRepository.clearDataStore()
        }
    }

    private fun saveLoginToken(jwtToken: String, refreshToken: String) {
        viewModelScope.launch {
            userRepository.saveToken(listOf(jwtToken, refreshToken))
        }
    }

    private fun saveLoginEmail(email: String) {
        viewModelScope.launch {
            userRepository.saveEmail(email)
        }
    }

    private fun saveUserId(id: Long) {
        viewModelScope.launch {
            userRepository.saveUserId(id)
        }
    }
}