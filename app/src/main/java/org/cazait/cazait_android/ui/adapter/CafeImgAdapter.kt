package org.cazait.cazait_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.R

class CafeImgAdapter(var cafeImg: MutableList<Int>) :
    RecyclerView.Adapter<CafeImgAdapter.ImgViewHolder>() {
    inner class ImgViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_cafe_img, parent, false)
    ) {
        val cafeImg = itemView.findViewById<ImageView>(R.id.cafe_info_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ImgViewHolder((parent))

    override fun onBindViewHolder(holder: CafeImgAdapter.ImgViewHolder, position: Int) {
        holder.cafeImg.setImageResource(cafeImg[position])
    }

    override fun getItemCount(): Int = cafeImg.size
}