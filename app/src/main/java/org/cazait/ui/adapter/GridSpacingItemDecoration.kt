package org.cazait.ui.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(private val dpSpace: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % 2 // item column

        outRect.left = dpSpace - column * dpSpace / 2
        outRect.right = (column + 1) * dpSpace / 2

        if (position < 2) { // top edge
            outRect.top = dpSpace
        }
        outRect.bottom = dpSpace // item bottom
    }
}
