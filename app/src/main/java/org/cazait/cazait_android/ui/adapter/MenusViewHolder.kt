package org.cazait.cazait_android.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.data.model.CafeMenu
import org.cazait.cazait_android.databinding.ItemCafeInfoMenuBinding

class MenusViewHolder(val binding: ItemCafeInfoMenuBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CafeMenu) {
        binding.menu = item
    }
}