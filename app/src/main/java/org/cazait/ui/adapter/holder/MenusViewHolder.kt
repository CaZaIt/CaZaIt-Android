package org.cazait.ui.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import org.cazait.databinding.ItemCafeInfoMenuBinding
import org.cazait.data.model.CafeMenu

class MenusViewHolder(val binding: ItemCafeInfoMenuBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CafeMenu) {
        binding.menu = item
    }
}
