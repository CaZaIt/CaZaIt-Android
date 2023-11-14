package org.cazait.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CafeImageRes(
    val cafeImageId: Long,
    val imageUrl: String,
) : Parcelable
