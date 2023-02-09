package org.cazait.cazait_android.data.model.remote.response

data class TokenResponse(
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
