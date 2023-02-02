package org.cazait.cazait_android.data.dto.cafe

import com.google.gson.annotations.SerializedName

data class CafeMenus(
    @SerializedName("data")
    val menus: List<CafeMenu>,
    val message: String,
    val result: String
) {
    override fun toString() = "message:$message result:$result"
}
