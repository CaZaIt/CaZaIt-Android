package org.cazait.cazait_android.ui.viewmodel

import org.cazait.cazait_android.ui.base.BaseViewModel

class ReviewData(
    private val score: Int,
    private val town: String,
    private val nickName: String,
    private val time: String,
    private val mainText: String
) {
    fun getScore(): Int {
        return score
    }

    fun getTown(): String {
        return town
    }

    fun getNickName(): String {
        return nickName
    }

    fun getTime(): String {
        return time
    }

    fun getMainText(): String {
        return mainText
    }
}

class CafeInfoReviewViewModel : BaseViewModel() {
    var reviewList: ArrayList<ReviewData> = arrayListOf(
        ReviewData(4, "화양동", "뚜벅초", "3시간 전", "카페 분위기가 너무 좋네요~~ 또 오고 싶어요^^"),
        ReviewData(5,"군자동","우가","5시간 전","카페 분위기가 그냥 좋네요^^ 담에 또 올게요~"),
        ReviewData(5,"광진동","시가","6시간 전","카페 분위기가 그냥 좋네요^^ 담에 또 올게요~"),
        ReviewData(5,"세종동","집가","7시간 전","카페 분위기가 그냥 좋네요^^ 담에 또 올게요~"),
        ReviewData(5,"서울동","카페가","9시간 전","카페 분위기가 그냥 좋네요^^ 담에 또 올게요~"),
        ReviewData(5,"청담동","학교가","10시간 전","카페 분위기가 그냥 좋네요^^ 담에 또 올게요~"),
        ReviewData(5,"북한동","초벅뚜","13시간 전","카페 분위기가 그냥 좋네요^^ 담에 또 올게요~"),
        ReviewData(5,"독도동","비누","13시간 전","카페 분위기가 그냥 좋네요^^ 담에 또 올게요~"),
        ReviewData(5,"울릉동","서기","15시간 전","카페 분위기가 그냥 좋네요^^ 담에 또 올게요~"),

        )
}