package org.cazait.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cazait.databinding.ItemCafeRecentBinding
import org.cazait.data.model.Cafe

class CafeRecentlyAdapter :
    RecyclerView.Adapter<CafeRecentlyAdapter.CafeRecentlyViewHolder>() {

    private val _recently = mutableListOf<Cafe>()
    var recently: List<Cafe> = _recently
        set(value) {
            _recently.clear()
            _recently.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeRecentlyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val recentlyItemBinding = ItemCafeRecentBinding.inflate(inflater, parent, false)

        return CafeRecentlyViewHolder(recentlyItemBinding)
    }

    override fun onBindViewHolder(
        holder: CafeRecentlyViewHolder,
        position: Int,
    ) {
        holder.bind(_recently[position])
    }

    override fun getItemCount() = _recently.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class CafeRecentlyViewHolder(val binding: ItemCafeRecentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cafe: Cafe) {
            binding.cafe = cafe
        }
    }
}
