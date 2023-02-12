package org.cazait.cazait_android.ui.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.data.model.ReviewData
import org.cazait.cazait_android.databinding.ItemCafeInfoRatingReviewBinding

class ReviewsViewHolder(val binding: ItemCafeInfoRatingReviewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ReviewData) {
        binding.review = item
    }
}