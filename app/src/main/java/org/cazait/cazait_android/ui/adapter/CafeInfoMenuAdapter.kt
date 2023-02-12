package org.cazait.cazait_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.data.model.CafeMenu
import org.cazait.cazait_android.databinding.ItemCafeInfoMenuBinding
import org.cazait.cazait_android.ui.adapter.holder.MenusViewHolder
import org.cazait.cazait_android.ui.viewmodel.CafeInfoMenuViewModel

class CafeInfoMenuAdapter(
    private val cafeInfoMenuViewModel: CafeInfoMenuViewModel,
    private val menus: List<CafeMenu>
) : RecyclerView.Adapter<MenusViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenusViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ItemCafeInfoMenuBinding.inflate(inflater, parent, false)

        return MenusViewHolder(listItemBinding)
    }

    // ViewHolder의 bind 메소드를 호출한다.
    override fun onBindViewHolder(holder: MenusViewHolder, position: Int) {
        holder.bind(menus[position])
    }

    override fun getItemCount() = menus.size

    // RecyclerView의 재사용 문제 해결
    override fun getItemViewType(position: Int): Int {
        return position
    }
}