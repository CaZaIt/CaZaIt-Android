package org.cazait.cazait_android.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.R
import org.cazait.cazait_android.ui.viewmodel.ReviewData

class CafeInfoReviewRVAdapter(private val list: MutableList<ReviewData>): RecyclerView.Adapter<CafeInfoReviewRVAdapter.ReviewItemViewHolder>() {
    inner class ReviewItemViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!){
        val town: TextView = itemView!!.findViewById(R.id.tv_town)
        val nickName: TextView = itemView!!.findViewById(R.id.tv_nickname)
        val time: TextView = itemView!!.findViewById(R.id.tv_time)
        val mainText: TextView = itemView!!.findViewById(R.id.tv_main_review)

        fun bind(data: ReviewData){
            town.text = data.getTown()
            nickName.text = data.getNickName()
            time.text = data.getTime()
            mainText.text = data.getMainText()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cafe_info_rating_review,parent,false)
        return ReviewItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewItemViewHolder, position: Int) {
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