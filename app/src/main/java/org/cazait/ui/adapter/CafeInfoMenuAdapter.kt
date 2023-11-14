package org.cazait.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.cazait.databinding.ItemCafeInfoMenuBinding
import org.cazait.data.model.CafeMenu
import org.cazait.ui.adapter.holder.MenusViewHolder
import org.cazait.ui.view.cafelist.info.inner.CafeMenuFragment
import org.cazait.ui.viewmodel.CafeInfoMenuViewModel

class CafeInfoMenuAdapter(
    private val context: CafeMenuFragment,
    private val cafeInfoMenuViewModel: CafeInfoMenuViewModel,
    private val menus: List<CafeMenu>,
) : RecyclerView.Adapter<MenusViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenusViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ItemCafeInfoMenuBinding.inflate(inflater, parent, false)

        return MenusViewHolder(listItemBinding)
    }

    // ViewHolder의 bind 메소드를 호출한다.
    override fun onBindViewHolder(holder: MenusViewHolder, position: Int) {
        val imgUrl = menus[position].image
        Glide.with(context).load(imgUrl).into(holder.binding.ivCafeMenu)
        holder.bind(menus[position])
    }

    override fun getItemCount() = menus.size

    // RecyclerView의 재사용 문제 해결
    override fun getItemViewType(position: Int): Int {
        return position
    }
}
