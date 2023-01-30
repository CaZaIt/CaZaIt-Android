package org.cazait.cazait_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.cazait.cazait_android.data.model.CafeState
import org.cazait.cazait_android.databinding.ItemCafeMainBinding

class CafeListItemAdapter : RecyclerView.Adapter<CafeListItemAdapter.ItemViewHolder>() {

    private val _data = mutableListOf<CafeState>()
    var data: List<CafeState> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

<<<<<<< HEAD
=======
    private var clickListener: ((id: Int) -> Unit)? = null

    fun setClickListener(listener: ((id: Int) -> Unit)) {
        this.clickListener = listener
>>>>>>> 8c42482 ([Feat] #33 - LiveData + DataBinding + RecyclerView)
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
    }

    inner class ItemViewHolder(val binding: ItemCafeMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CafeState) {
            binding.cafeState = item
        }
    }
}