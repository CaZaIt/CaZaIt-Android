package org.cazait.cazait_android.ui.base

import org.cazait.cazait_android.data.model.Cafe

interface RecyclerItemListener {
    fun onItemSelected(cafe: Cafe)
}