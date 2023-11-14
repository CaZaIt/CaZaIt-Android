package org.cazait.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.cazait.R
import org.cazait.data.model.CafeImageRes

class CafeImgAdapter(private val context: Context, private val cafeImg: List<CafeImageRes>) :
    RecyclerView.Adapter<CafeImgAdapter.ImgViewHolder>() {
    inner class ImgViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.cafe_info_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_cafe_img, parent, false)
        return ImgViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImgViewHolder, position: Int) {
        val imgUrl = cafeImg[position].imageUrl
        Glide.with(context).load(imgUrl).into(holder.imageView)
    }

    override fun getItemCount(): Int = cafeImg.size
}
