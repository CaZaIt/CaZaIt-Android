package org.cazait.cazait_android.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class PostInterestCafeResponse(
    val code: Int,
    val data: Data,
    val message: String,
    val result: String
) {
    data class Data(
        @SerializedName("id") val favoritesId: Long
    )
}
