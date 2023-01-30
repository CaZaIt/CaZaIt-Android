package org.cazait.cazait_android

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "interest")
data class CafeInterestEntity(
    @PrimaryKey
    val name: String,
    val distance: String,
    val address: String,
    val state: String
)
