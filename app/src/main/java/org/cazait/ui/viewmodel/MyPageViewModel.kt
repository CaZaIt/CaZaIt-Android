package org.cazait.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.cazait.data.repository.UserRepository
import org.cazait.ui.base.BaseViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val ioDispatcher: CoroutineContext,
) : BaseViewModel() {

    private val _logoutCompleted = MutableLiveData<Boolean>()
    val logoutCompleted: LiveData<Boolean>
        get() = _logoutCompleted

    fun logout() {
        viewModelScope.launch {
            userRepository.logout()
            _logoutCompleted.postValue(true)
        }
    }
}
