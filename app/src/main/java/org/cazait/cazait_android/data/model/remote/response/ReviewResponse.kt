package org.cazait.cazait_android.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    @SerializedName("code") val code : Int,
    @SerializedName("data") val data: Data,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: String
) {
    data class Data(
        @SerializedName("reviewResponses") val reviewResponses: List<ReviewItem>,
        @SerializedName("totalElements") val totalElement: Int,
        @SerializedName("nextCursor") val nextCursor: Int
    ){
        data class ReviewItem(
            val cafeId: Int,
            val content: String,
            val score: Int,
            val userId: Int
        )
    }
}