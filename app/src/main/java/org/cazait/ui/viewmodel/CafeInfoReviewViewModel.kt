package org.cazait.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.cazait.data.Resource
import org.cazait.data.model.remote.request.ReviewRequest
import org.cazait.data.model.remote.response.ReviewResponse
import org.cazait.data.repository.DataRepository
import org.cazait.ui.base.BaseViewModel
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
        Log.d("request 데이터", "$request")
        viewModelScope.launch {
            _reviewList.value = Resource.Loading()
            dataRepository.getReviews(cafeId, query = request).collect {
                _reviewList.value = it
            }
        }
    }
}
