package org.cazait.cazait_android.ui.adapter.binding

import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import org.cazait.cazait_android.R

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

    @BindingAdapter("favorite_background")
    @JvmStatic
    fun AppCompatCheckBox.setBackground(isFavorite: Boolean) {
        val background = when(isFavorite) {
            true -> R.drawable.ic_interest_clicked
            false -> R.drawable.ic_interest_unclicked
        }
        setBackground(ContextCompat.getDrawable(context, background))
    }
}