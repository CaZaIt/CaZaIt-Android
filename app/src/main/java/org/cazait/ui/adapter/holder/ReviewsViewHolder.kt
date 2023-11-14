package org.cazait.ui.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import org.cazait.databinding.ItemCafeInfoRatingReviewBinding
import org.cazait.data.model.ReviewData

class ReviewsViewHolder(val binding: ItemCafeInfoRatingReviewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ReviewData) {
        binding.review = item
    }
}
