package org.cazait.cazait_android.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class MenuResponse(
    @SerializedName("data") val data: List<Data>,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: String
) {
    data class Data(
        val cafeMenuId: Int,
        val description: String,
        val imageUrl: String,
        val name: String,
        val price: Int
    )
}
