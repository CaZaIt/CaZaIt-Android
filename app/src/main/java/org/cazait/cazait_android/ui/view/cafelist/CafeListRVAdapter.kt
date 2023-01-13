package org.cazait.cazait_android.ui.view.cafelist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.R

class CafeListRVAdapter(private val list: MutableList<CafeData>):RecyclerView.Adapter<CafeListRVAdapter.ListItemViewHolder>() {

    inner class ListItemViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView!!){
        //        var cafeImg: ImageView? = itemView!!.findViewById(R.id.imgv_cafe)
        var cafeName: TextView = itemView!!.findViewById(R.id.tv_cafe_name)
        var dis: TextView = itemView!!.findViewById(R.id.tv_cafe_dis)
        var add: TextView = itemView!!.findViewById(R.id.tv_cafe_add)
        var cong: TextView = itemView!!.findViewById(R.id.tv_cafe_cong)

        fun bind(data: CafeData){
            cafeName.text = data.getCafeName()
            dis.text = data.getDistance()
            add.text = data.getAddress()
            cong.text = data.getCongestion()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cafe_item_data, parent, false)
        return ListItemViewHolder(view)
    }

    // ViewHolder의 bind 메소드를 호출한다.
    override fun onBindViewHolder(holder: CafeListRVAdapter.ListItemViewHolder, position: Int) {
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