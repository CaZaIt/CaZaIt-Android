package org.cazait.cazait_android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.remote.request.ReviewEditRequest
import org.cazait.cazait_android.data.model.remote.response.ReviewEditResponse
import org.cazait.cazait_android.data.repository.DataRepository
import org.cazait.cazait_android.data.repository.UserRepository
import org.cazait.cazait_android.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CafeInfoReviewEditViewModel @Inject constructor(
    private val dataRepository: DataRepository,
    private val userRepository: UserRepository
) : BaseViewModel() {
    private val _editProcess = MutableLiveData<Resource<ReviewEditResponse>>()
    val editProcess: LiveData<Resource<ReviewEditResponse>>
        get() = _editProcess

    fun editReview(cafeId: Long, content: String, score: Int) {
        viewModelScope.launch {
            val userId = userRepository.fetchUserIdInDataStore().first()
            _editProcess.value = Resource.Loading()
            dataRepository.postReview(userId, cafeId, body = ReviewEditRequest(content, score)).collect {
                _editProcess.value = it
            }
        }
    }
}