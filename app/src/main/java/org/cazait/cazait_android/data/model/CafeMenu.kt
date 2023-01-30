package org.cazait.cazait_android.data.model

data class CafeMenu(
    val name: String,
    val price: Int,
    val desc: String
    // val image: ????
) {
    fun getStringPrice() = price.toString()
}
