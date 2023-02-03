package org.cazait.cazait_android.data.dto.cafe

import com.google.gson.annotations.SerializedName

data class Cafes(
    @SerializedName("data")
    val cafes: List<Cafe>,
    val message: String,
    val result: String
)