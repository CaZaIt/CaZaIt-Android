package org.cazait.ui.base

import org.cazait.data.model.Cafe

interface RecyclerItemListener {
    fun onItemSelected(cafe: Cafe)
}
