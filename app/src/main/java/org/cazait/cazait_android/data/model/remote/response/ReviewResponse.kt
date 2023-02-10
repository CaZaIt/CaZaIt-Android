package org.cazait.cazait_android.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    @SerializedName("data") val data: List<Data>,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: String
) {
    data class Data(
        val cafeId: Int,
        val content: String,
        val score: Int,
        val userId: Int
    )
}
