package org.cazait.cazait_android.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.R
import org.cazait.cazait_android.ui.viewmodel.MenuData

class CafeInfoMenuRVAdapter(private val list: MutableList<MenuData>): RecyclerView.Adapter<CafeInfoMenuRVAdapter.MenuItemViewHolder>() {
    inner class MenuItemViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!){
        //        val menuImg:ImageView = itemView!!.findViewById(R.id.imgv_cafe_menu)
        val menuName: TextView = itemView!!.findViewById(R.id.tv_cafe_menu)
        val menuPrice: TextView = itemView!!.findViewById(R.id.tv_cafe_menu_price)

        fun bind(data: MenuData){
            menuName.text = data.getMenuName()
            menuPrice.text = data.getMenuPrice()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cafe_info_menu, parent, false)
        return MenuItemViewHolder(view)
    }

    // ViewHolder의 bind 메소드를 호출한다.
    override fun onBindViewHolder(holder: CafeInfoMenuRVAdapter.MenuItemViewHolder, position: Int) {
        Log.d("ListAdapter", "===== ===== ===== ===== onBindViewHolder ===== ===== ===== =====")
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    // RecyclerView의 재사용 문제 해결
    override fun getItemViewType(position: Int): Int {
        return position
    }
}