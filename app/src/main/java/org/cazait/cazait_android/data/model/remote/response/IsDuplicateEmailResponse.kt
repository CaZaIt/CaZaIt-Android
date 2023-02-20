package org.cazait.cazait_android.data.model.remote.response

data class IsDuplicateEmailResponse(
    val code: Int,
    val result: String,
    val message: String,
    val data: String
)
