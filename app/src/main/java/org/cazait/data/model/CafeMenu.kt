package org.cazait.data.model

data class CafeMenu(
    val desc: String,
    val name: String,
    val price: Int,
    val image: String,
) {
    fun getStringPrice() = price.toString()
}
