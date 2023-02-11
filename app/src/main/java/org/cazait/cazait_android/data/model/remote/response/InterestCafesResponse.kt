package org.cazait.cazait_android.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class InterestCafesResponse(
    val code: Int,
    val result: String,
    val message: String,
    val data: List<Data>
) {
    data class Data(
        val address: String,
        val cafeId: Long,
        val congestion: String,
        val favoritesId: Long,
        val imageUrl: List<String>,
        val latitude: String,
        val longitude: String,
        @SerializedName("name") val cafeName: String
    )
}
