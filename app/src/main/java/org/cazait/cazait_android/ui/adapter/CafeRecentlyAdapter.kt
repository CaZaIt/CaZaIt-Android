package org.cazait.cazait_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.data.model.CafeState
import org.cazait.cazait_android.databinding.ItemCafeRecentlyBinding

class CafeRecentlyAdapter :
    RecyclerView.Adapter<CafeRecentlyAdapter.CafeRecentlyViewHolder>() {

    private val _recently = mutableListOf<CafeState>()
    var recently: List<CafeState> = _recently
        set(value) {
            _recently.clear()
            _recently.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeRecentlyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val recentlyItemBinding = ItemCafeRecentlyBinding.inflate(inflater, parent, false)

        return CafeRecentlyViewHolder(recentlyItemBinding)
    }


    override fun onBindViewHolder(
        holder: CafeRecentlyViewHolder,
        position: Int
    ) {
        holder.bind(_recently[position])

    }

    override fun getItemCount() = _recently.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class CafeRecentlyViewHolder(val binding: ItemCafeRecentlyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cafeState: CafeState) {
            binding.cafeState = cafeState

        }
    }
}