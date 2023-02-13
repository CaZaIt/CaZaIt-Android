package org.cazait.cazait_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.data.model.ReviewData
import org.cazait.cazait_android.databinding.ItemCafeInfoRatingReviewBinding
import org.cazait.cazait_android.ui.adapter.holder.ReviewsViewHolder
import org.cazait.cazait_android.ui.viewmodel.CafeInfoReviewViewModel

class CafeInfoReviewAdapter(
    private val cafeInfoReviewViewModel: CafeInfoReviewViewModel,
    private val reviews: List<ReviewData>
): RecyclerView.Adapter<ReviewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ItemCafeInfoRatingReviewBinding.inflate(inflater, parent, false)

        return ReviewsViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        holder.binding.itemRatingBar.rating = reviews[position].score.toFloat()
        holder.binding.tvNickname.text = reviews[position].nickName
        holder.bind(reviews[position])
    }

    override fun getItemCount() = reviews.size

    // RecyclerView의 재사용 문제 해결
    override fun getItemViewType(position: Int): Int {
        return position
    }

//    inner class ReviewItemViewHolder(val binding: ItemCafeInfoRatingReviewBinding): RecyclerView.ViewHolder(binding.root){
//        fun bind(reviewData: ReviewData){
//            binding.review = reviewData
//        }
//    }
}