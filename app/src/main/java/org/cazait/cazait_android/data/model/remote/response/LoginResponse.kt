package org.cazait.cazait_android.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("data")
    val data: Data,
    val message: String,
    val result: String
) {
    data class Data(
        val email: String,
        val id: Long,
        val jwtToken: String,
        val refreshToken: String
    )
}