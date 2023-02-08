package org.cazait.cazait_android.data.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cafe(
    val id: Long,
    val name: String,
    val distance: Int,
    val address: String,
    val state: String
): Parcelable

/**
 * Parcelable is an Android interface that allows you to pass complex data structures between activities, services, and other Android components.
 * It's similar to Serializable, but it's more efficient because it uses the Android Parcel object to serialize and deserialize data, which is faster than the Java serialization mechanism used by Serializable.
 * In order to use Parcelable, you need to create a custom implementation of the interface for the class you want to pass as an extra.
 * The implementation should include methods to write the class data to a Parcel object and to read the class data from a Parcel object.
 */