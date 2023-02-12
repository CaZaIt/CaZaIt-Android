package org.cazait.cazait_android.data.model

data class CafeMenu(
    val desc: String,
    val name: String,
    val price: Int
//     val image: Int
) {
    fun getStringPrice() = price.toString()
}
