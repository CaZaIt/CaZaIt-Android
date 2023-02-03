package org.cazait.cazait_android.data.model

data class User(
    val email: String,
    val nickname: String? = null,
    val password: String
)
