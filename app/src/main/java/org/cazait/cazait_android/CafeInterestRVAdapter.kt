package org.cazait.cazait_android

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.data.model.CafeState
import org.cazait.cazait_android.databinding.ItemCafeInterestBinding


class CafeInterestRVAdapter(
    private val context: Context,
    private val dataset: List<CafeState>
) : RecyclerView.Adapter<CafeInterestRVAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ItemCafeInterestBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CafeState) {
            binding.cafeState = item
}
            }


    override fun getItemCount() = dataset.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val interestItemBinding = ItemCafeInterestBinding
            .inflate(inflater, parent, false)

        return ItemViewHolder(interestItemBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataset[position])
    }
}