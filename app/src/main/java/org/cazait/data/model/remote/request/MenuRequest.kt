package org.cazait.data.model.remote.request

import com.google.gson.annotations.SerializedName

data class MenuRequest(
    @SerializedName("cafeId")
    val cafeId: Long,
)
