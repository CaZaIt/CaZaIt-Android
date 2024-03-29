package org.cazait.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cafe(
    val id: Long,
    val name: String,
    val distance: Int,
    val address: String,
    val state: String,
    var favorite: Boolean = false, // 좋아요 버튼을 위해 var로 설정
    val favoritesId: Long = -1,
    @SerializedName("getCafeImageRes") val cafeImageRes: List<CafeImageRes>,
    val latitude: String,
    val longitude: String,
) : Parcelable {
    fun getStringDistance() = distance.toString()
    fun getKorState(): String = stateMapper.getValue(state)
}

val stateMapper: Map<String, String>
    get() = mapOf(
        "FREE" to "여유",
        "NORMAL" to "보통",
        "CROWDED" to "혼잡",
        "VERY_CROWDED" to "매우 혼잡",
    ).withDefault { "보통" }

/**
 * Parcelable is an Android interface that allows you to pass complex data structures between activities, services, and other Android components.
 * It's similar to Serializable, but it's more efficient because it uses the Android Parcel object to serialize and deserialize data, which is faster than the Java serialization mechanism used by Serializable.
 * In order to use Parcelable, you need to create a custom implementation of the interface for the class you want to pass as an extra.
 * The implementation should include methods to write the class data to a Parcel object and to read the class data from a Parcel object.
 *
 * Ex) putExtra()
 */
