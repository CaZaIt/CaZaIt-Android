package org.cazait.cazait_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.data.model.CafeMenu
import org.cazait.cazait_android.databinding.ItemCafeInfoMenuBinding

class CafeInfoMenuAdapter :
    RecyclerView.Adapter<CafeInfoMenuAdapter.MenuItemViewHolder>() {

    private val _data = mutableListOf<CafeMenu>()
    var data: List<CafeMenu> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ItemCafeInfoMenuBinding.inflate(inflater, parent, false)

        return MenuItemViewHolder(listItemBinding)
    }

    // ViewHolder의 bind 메소드를 호출한다.
    override fun onBindViewHolder(holder: CafeInfoMenuAdapter.MenuItemViewHolder, position: Int) {
        // Log.d("ListAdapter", "===== ===== ===== ===== onBindViewHolder ===== ===== ===== =====")
        holder.bind(_data[position])
    }

    override fun getItemCount() = _data.size

    // RecyclerView의 재사용 문제 해결
    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class MenuItemViewHolder(val binding: ItemCafeInfoMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cafeMenu: CafeMenu) {
            binding.menu = cafeMenu
        }
    }
}