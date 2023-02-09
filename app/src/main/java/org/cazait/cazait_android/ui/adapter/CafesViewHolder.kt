package org.cazait.cazait_android.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.data.model.Cafe
import org.cazait.cazait_android.databinding.ItemCafeMainBinding
import org.cazait.cazait_android.ui.base.RecyclerItemListener

class CafesViewHolder(val binding: ItemCafeMainBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Cafe, recyclerItemListener: RecyclerItemListener) {
        binding.cafe = item
        binding.root.setOnClickListener { recyclerItemListener.onItemSelected(item) }
    }
}