package org.cazait.cazait_android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.cazait.cazait_android.data.model.ReviewData
import org.cazait.cazait_android.data.repository.DataRepositorySource
import org.cazait.cazait_android.ui.base.BaseViewModel
import javax.inject.Inject


@HiltViewModel
class CafeInfoReviewViewModel @Inject constructor(private val dataRepository: DataRepositorySource) :
    BaseViewModel() {
    private val list = arrayListOf(
        ReviewData(4, "화양동", "뚜벅초", "3시간 전", "카페 분위기가 너무 좋네요~~ 또 오고 싶어요^^"),
        ReviewData(5, "군자동", "우가", "5시간 전", "카페 분위기가 그냥 좋네요^^ 담에 또 올게요~"),
        ReviewData(5, "광진동", "시가", "6시간 전", "카페 분위기가 그냥 좋네요^^ 담에 또 올게요~"),
        ReviewData(5, "세종동", "집가", "7시간 전", "카페 분위기가 그냥 좋네요^^ 담에 또 올게요~"),
        ReviewData(5, "서울동", "카페가", "9시간 전", "카페 분위기가 그냥 좋네요^^ 담에 또 올게요~"),
        ReviewData(5, "청담동", "학교가", "10시간 전", "카페 분위기가 그냥 좋네요^^ 담에 또 올게요~"),
        ReviewData(5, "북한동", "초벅뚜", "13시간 전", "카페 분위기가 그냥 좋네요^^ 담에 또 올게요~"),
        ReviewData(5, "독도동", "비누", "13시간 전", "카페 분위기가 그냥 좋네요^^ 담에 또 올게요~"),
        ReviewData(5, "울릉동", "서기", "15시간 전", "카페 분위기가 그냥 좋네요^^ 담에 또 올게요~"),
    )
    private val _reviewList = MutableLiveData<ArrayList<ReviewData>>()
    val reviewList: LiveData<ArrayList<ReviewData>>
        get() = _reviewList

    init {
        setReviewList(list)
    }

    // 만일 추가할 일이 있다면 다음 함수 사용
    private fun addReview(review: ReviewData) {
        list.add(review)
        _reviewList.postValue(list)
    }

    private fun setReviewList(dataset: ArrayList<ReviewData>) {
        _reviewList.postValue(dataset)
    }
}