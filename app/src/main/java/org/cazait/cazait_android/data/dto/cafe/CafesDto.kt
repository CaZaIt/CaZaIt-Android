package org.cazait.cazait_android.data.dto.cafe

import com.google.gson.annotations.SerializedName

data class CafesDto(
    @SerializedName("data")
    val cafes: List<CafeDto>,
    val message: String,
    val result: String
)