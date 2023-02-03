package org.cazait.cazait_android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.internal.operators.single.SingleDoOnEvent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.User
import org.cazait.cazait_android.data.model.remote.request.LoginRequest
import org.cazait.cazait_android.data.model.remote.response.LoginResponse
import org.cazait.cazait_android.data.repository.DataRepository
import org.cazait.cazait_android.ui.base.BaseViewModel
import org.cazait.cazait_android.ui.util.SingleEvent
import org.cazait.cazait_android.usecase.errors.ErrorManager
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val dataRepository: DataRepository) :
    BaseViewModel() {

    private val _loginProcess = MutableLiveData<Resource<LoginResponse>>()
    val loginProcess: LiveData<Resource<LoginResponse>>
        get() = _loginProcess

    private val _showToast = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = _showToast

    fun postSignUp() {

    }

    fun postLogIn(email: String, password: String) {
        viewModelScope.launch {
            _loginProcess.value = Resource.Loading()
            dataRepository.postLogin(body = LoginRequest(email, password)).collect {
                _loginProcess.value = it
            }
        }
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        _showToast.value = SingleEvent(error.description)
    }

    fun showToastMessage(errorMessage: String?) {
        if(errorMessage == null) return
        _showToast.value = SingleEvent(errorMessage)
    }
}

const val ADMIN_ID = "vv99911@gmail.com"
const val ADMIN_PASS = "asdqwe123!"