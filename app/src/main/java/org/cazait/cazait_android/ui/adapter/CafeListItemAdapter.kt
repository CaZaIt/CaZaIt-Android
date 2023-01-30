package org.cazait.cazait_android.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.data.model.CafeState
import org.cazait.cazait_android.databinding.ItemCafeMainBinding
import org.cazait.cazait_android.ui.view.cafelist.info.CafeInformationActivity

class CafeListItemAdapter : RecyclerView.Adapter<CafeListItemAdapter.ItemViewHolder>() {

    private val _data = mutableListOf<CafeState>()
    var data: List<CafeState> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    private var clickListener: ((id: Int) -> Unit)? = null

    fun setClickListener(listener: ((id: Int) -> Unit)) {
        this.clickListener = listener
    }

    override fun getItemCount() = _data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ItemCafeMainBinding
            .inflate(inflater, parent, false)

        return ItemViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(_data[position])

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, CafeInformationActivity::class.java)

            context.startActivity(intent)
        }
    }

    inner class ItemViewHolder(val binding: ItemCafeMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CafeState) {
            binding.cafeState = item
        }
    }
}