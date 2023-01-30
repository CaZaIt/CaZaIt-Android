package org.cazait.cazait_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.data.model.ReviewData
import org.cazait.cazait_android.databinding.ItemCafeInfoRatingReviewBinding

class CafeInfoReviewAdapter: RecyclerView.Adapter<CafeInfoReviewAdapter.ReviewItemViewHolder>() {
    private val _data = mutableListOf<ReviewData>()
    var data: List<ReviewData> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ItemCafeInfoRatingReviewBinding.inflate(inflater, parent, false)

        return ReviewItemViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ReviewItemViewHolder, position: Int) {
        holder.bind(_data[position])
    }

    override fun getItemCount() = _data.size

    // RecyclerView의 재사용 문제 해결
    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ReviewItemViewHolder(val binding: ItemCafeInfoRatingReviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(reviewData: ReviewData){
            binding.review = reviewData
        }
    }
}