package org.cazait.cazait_android.ui.adapter.holder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.data.model.Cafe
import org.cazait.cazait_android.databinding.ItemCafeInterestBinding
import org.cazait.cazait_android.databinding.ItemCafeMainBinding
import org.cazait.cazait_android.ui.base.RecyclerItemListener

class CafesViewHolder(
    val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bindForCafeListPage(item: Cafe, recyclerItemListener: RecyclerItemListener) {
        (binding as ItemCafeMainBinding).cafe = item
        binding.root.setOnClickListener { recyclerItemListener.onItemSelected(item) }
    }

    fun bindForInterestPage(item: Cafe, recyclerItemListener: RecyclerItemListener) {
        (binding as ItemCafeInterestBinding).cafe = item
        binding.root.setOnClickListener { recyclerItemListener.onItemSelected(item) }
    }
}