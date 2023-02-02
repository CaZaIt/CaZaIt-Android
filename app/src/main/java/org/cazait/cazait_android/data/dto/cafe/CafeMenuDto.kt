package org.cazait.cazait_android.data.dto.cafe

import com.google.gson.annotations.SerializedName

data class CafeMenuDto(
    @SerializedName("cafeMenuId")
    val id: Long,
    val description: String,
    val imageUrl: String,
    val name: String,
    val price: Int
)
