package org.cazait.data.model.remote.request

data class SignUpRequest(
    val email: String,
    val nickname: String,
    val password: String,
)
