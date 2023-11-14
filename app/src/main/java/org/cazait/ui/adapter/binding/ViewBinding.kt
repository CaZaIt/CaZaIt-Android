package org.cazait.ui.adapter.binding

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import org.cazait.R

object ViewBinding {
    @BindingAdapter("stateColor")
    @JvmStatic
    fun TextView.setStateColor(state: String) {
        val colorRes = when (state) {
            "FREE" -> R.color.cafe_list_item_free
            "NORMAL" -> R.color.cafe_list_item_normal
            "CROWDED" -> R.color.cafe_list_item_crowded
            "VERY_CROWDED" -> R.color.cafe_list_item_very_crowded
            else -> R.color.cafe_list_item_normal
        }
        setTextColor(ContextCompat.getColor(context, colorRes))
    }
}
