package org.cazait.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class DeleteInterestCafeResponse(
    val code: Int,
    @SerializedName("data") val deleteMessage: String,
    val message: String,
    val result: String,
)
