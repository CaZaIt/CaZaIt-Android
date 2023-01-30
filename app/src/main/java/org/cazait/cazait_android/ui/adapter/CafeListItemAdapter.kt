package org.cazait.cazait_android.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.cazait.cazait_android.data.model.CafeState
import org.cazait.cazait_android.databinding.ItemCafeMainBinding

class CafeListItemAdapter(
    private val context: Context,
    private val dataset: List<CafeState>
) : RecyclerView.Adapter<CafeListItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ItemCafeMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CafeState) {
            binding.cafeState = item
        }

    }

    override fun getItemCount() = dataset.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ItemCafeMainBinding
            .inflate(inflater, parent, false)

        return ItemViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataset[position])
    }
}