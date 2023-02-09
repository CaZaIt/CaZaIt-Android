package org.cazait.cazait_android.data.model.remote.request

data class CafeListRequest(
    val latitude: String,
    val longitude: String,
    val limit: String,
    val sort: String
)