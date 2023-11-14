package org.cazait.data.model

data class User(
    val email: String,
    val nickname: String? = null,
    val password: String,
)
