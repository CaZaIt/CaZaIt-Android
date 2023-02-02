package org.cazait.cazait_android.data.dto.cafe

import com.google.gson.annotations.SerializedName

data class Cafe(
    @SerializedName("cafeId")
    val id: Long,
    val congestionId: Long,
    val latitude: String,
    val location: String,
    val name: String
)
