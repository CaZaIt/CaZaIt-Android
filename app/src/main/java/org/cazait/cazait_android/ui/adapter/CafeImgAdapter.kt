package org.cazait.cazait_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.R
import org.cazait.cazait_android.data.model.CafeImg

class CafeImgAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val _image = mutableListOf<CafeImg>().apply {
        add(CafeImg(R.drawable.image_cafe_ex1))
        add(CafeImg(R.drawable.image_cafe_ex2))
        add(CafeImg(R.drawable.image_cafe_ex2))
        add(CafeImg(R.drawable.image_cafe_ex2))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cafe_img, parent, false)
        ){}
    }

    override fun getItemCount() = _image.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }
}