package org.cazait.cazait_android.ui.adapter

import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.InterestCheckStatus
import org.cazait.cazait_android.data.model.CafeState
import org.cazait.cazait_android.databinding.ItemCafeMainBinding

class CafeListItemAdapter(
    private val context: Context,
    private val dataset: List<CafeState>,

) : RecyclerView.Adapter<CafeListItemAdapter.ItemViewHolder>() {
    private val checkboxStatus = SparseBooleanArray()

    inner class ItemViewHolder(val binding: ItemCafeMainBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CafeState) {
            binding.cafeState = item

            binding.btnCafeMainFavorite.isChecked = checkboxStatus[adapterPosition]

            binding.btnCafeMainFavorite.setOnClickListener {
                if (!binding.btnCafeMainFavorite.isChecked)
                    checkboxStatus.put(adapterPosition, false)
                else
                    checkboxStatus.put(adapterPosition, true)
                notifyItemChanged(adapterPosition)
            }

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