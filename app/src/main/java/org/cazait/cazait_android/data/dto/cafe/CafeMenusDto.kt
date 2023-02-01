package org.cazait.cazait_android.data.dto.cafe

import com.google.gson.annotations.SerializedName

data class CafeMenusDto(
    @SerializedName("data")
    val menus: List<CafeMenuDto>,
    val message: String,
    val result: String
) {
    override fun toString() = "message:$message result:$result"
}
