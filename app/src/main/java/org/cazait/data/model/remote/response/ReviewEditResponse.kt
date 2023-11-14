package org.cazait.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class ReviewEditResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("data") val data: Data,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: String,
) {
    data class Data(
        @SerializedName("cafeId") val cafeId: Long,
        @SerializedName("content") val content: String,
        @SerializedName("createdAt") val createdAt: String,
        @SerializedName("reviewId") val reviewId: Long,
        @SerializedName("score") val score: Int,
        @SerializedName("userId") val userId: Long,
    )
}
