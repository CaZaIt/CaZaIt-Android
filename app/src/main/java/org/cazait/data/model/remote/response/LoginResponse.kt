package org.cazait.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("data") val data: Data,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: String,
) {
    data class Data(
        @SerializedName("email") val email: String,
        @SerializedName("id") val id: Long,
        @SerializedName("jwtToken") val jwtToken: String,
        @SerializedName("refreshToken") val refreshToken: String,
    )
}
