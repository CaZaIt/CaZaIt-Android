package org.cazait.cazait_android.data.model

data class ReviewData(
    val score: Int,
    val town: String,
    val nickName: String,
    val time: String,
    val mainText: String
) {
    fun getStringScore() = score.toString()
}