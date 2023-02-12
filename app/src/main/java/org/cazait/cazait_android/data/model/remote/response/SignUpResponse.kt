package org.cazait.cazait_android.data.model.remote.response

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("data")
    val data: Data,
    val message: String,
    val result: String
) {
    data class Data(
        val email: String,
        val id: Long,
        val nickname: String,
        val password: String
    )
}
