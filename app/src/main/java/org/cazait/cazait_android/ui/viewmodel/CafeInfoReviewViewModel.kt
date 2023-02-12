package org.cazait.cazait_android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.ReviewData
import org.cazait.cazait_android.data.model.remote.request.ReviewRequest
import org.cazait.cazait_android.data.model.remote.response.ReviewResponse
import org.cazait.cazait_android.data.repository.DataRepository
import org.cazait.cazait_android.data.repository.DataRepositoryImpl
import org.cazait.cazait_android.ui.base.BaseViewModel
import javax.inject.Inject


@HiltViewModel
class CafeInfoReviewViewModel @Inject constructor(private val dataRepository: DataRepository) :
    BaseViewModel() {
    private val _reviewList = MutableLiveData<Resource<ReviewResponse>>()
    val reviewList: LiveData<Resource<ReviewResponse>>
        get() = _reviewList

    init {
        initDataSet()
    }

    private fun initDataSet() {

    }

    fun getReviews(cafeId: Long) {
        val request: ReviewRequest = ReviewRequest("newest")
        viewModelScope.launch {
            _reviewList.value = Resource.Loading()
            dataRepository.getReviews(cafeId, query = request).collect {
                _reviewList.value = it
            }
        }
    }
}