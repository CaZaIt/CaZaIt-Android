package org.cazait.cazait_android.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.R
import org.cazait.cazait_android.data.model.CafeImg

class CafeImgAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    data class Card(val id: Int)

    val items = mutableListOf<Card>().apply {
        repeat(10) { add(Card(it)) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cafe_img, parent, false)
        ) {}
    }

    override fun getItemCount() = items.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Empty
    }

//    private val _image = mutableListOf<CafeImg>()
//    var image: List<CafeImg> = _image
//        set(value) {
//            _image.clear()
//            _image.addAll(value)
//            notifyDataSetChanged()
//        }
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): CafeImgAdapter.ImageViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val listItemBinding = ItemCafeImgBinding
//            .inflate(inflater, parent, false)
//
//        return ImageViewHolder(listItemBinding)
//    }
//
//    override fun getItemCount() = _image.size
//
//    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
//        Log.d("Image","$_image")
//        holder.bind(_image[position])
//    }
//
//    inner class ImageViewHolder(val binding: ItemCafeImgBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(item: CafeImg) {
//            binding.cafeInfoImg = item.img
//        }
//    }
}