package org.cazait.data.model.remote.request

import com.google.gson.annotations.SerializedName

data class ReviewEditRequest(
    @SerializedName("content") val content: String,
    @SerializedName("score") val score: Int,
)
