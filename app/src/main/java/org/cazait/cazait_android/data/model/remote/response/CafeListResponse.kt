package org.cazait.cazait_android.data.model.remote.response

import com.google.gson.annotations.SerializedName
import org.cazait.cazait_android.data.model.CafeImageRes

data class CafeListResponse(
    @SerializedName("code") val code : Int,
    @SerializedName("data") val data: List<List<Data>>,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: String
) {
    data class Data(
        val address: String,
        val cafeId: Long,
        @SerializedName("congestionStatus") val congestionStatus: String,
        val distance: Int,
        val favorite: Boolean,
        @SerializedName("getCafeImageRes") val cafeImageRes: List<CafeImageRes>,
        val latitude: String,
        val longitude: String,
        val name: String
    )
}
