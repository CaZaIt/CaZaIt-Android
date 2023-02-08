package org.cazait.cazait_android.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class CafeListResponse(
    @SerializedName("data") val data: List<Data>,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: String
) {
    data class Data(
        val address: String,
        val cafeId: Long,
        @SerializedName("congestionStatus") val congestionStatus: String,
        val distance: Int,
        val favorite: Boolean,
        val latitude: String,
        val longitude: String,
        val name: String
    )
}
