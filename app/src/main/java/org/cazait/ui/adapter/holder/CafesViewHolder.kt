package org.cazait.ui.adapter.holder

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.cazait.R
import org.cazait.databinding.ItemCafeInterestBinding
import org.cazait.databinding.ItemCafeMainBinding
import org.cazait.data.model.Cafe
import org.cazait.ui.base.RecyclerItemListener
import org.cazait.ui.viewmodel.CafeInterestViewModel
import org.cazait.ui.viewmodel.CafeListViewModel

class CafesViewHolder(
    val binding: ViewDataBinding,
    val viewModel: ViewModel? = null,
) : RecyclerView.ViewHolder(binding.root) {
    fun bindForCafeListPage(item: Cafe, recyclerItemListener: RecyclerItemListener) {
        require(binding is ItemCafeMainBinding)
        require(viewModel is CafeListViewModel)

        binding.cafe = item

        if (item.cafeImageRes.isNotEmpty()) {
            val imgUrl = item.cafeImageRes[0].imageUrl
            Glide.with(itemView).load(imgUrl).into(binding.ivCafeLandscape)
        }

        val drawableOriginId =
            if (item.favorite) R.drawable.ic_interest_clicked else R.drawable.ic_interest_unclicked
        binding.btnCafeMainFavorite.setBackgroundResource(drawableOriginId)

        binding.btnCafeMainFavorite.setOnClickListener {
            item.favorite = !item.favorite
            val drawableNewId =
                if (item.favorite) R.drawable.ic_interest_clicked else R.drawable.ic_interest_unclicked
            binding.btnCafeMainFavorite.setBackgroundResource(drawableNewId)
            if (item.favorite) {
                viewModel.likeCafe(
                    userId = viewModel.userIdLiveData.value!!,
                    cafeId = item.id,
                )
            } else /* !item.favorite */ {
                viewModel.dislikeCafe(item.id)
            }
            binding.root.setOnClickListener { recyclerItemListener.onItemSelected(item) }
        }
    }

    fun bindForInterestPage(item: Cafe, recyclerItemListener: RecyclerItemListener) {
        require(binding is ItemCafeInterestBinding)
        require(viewModel is CafeInterestViewModel)

        binding.cafe = item

        if (item.cafeImageRes.isNotEmpty()) {
            val imgUrl = item.cafeImageRes[0].imageUrl
            Glide.with(itemView).load(imgUrl).into(binding.ivCafeInterestLandscape)
        }

        val drawableOriginId =
            if (item.favorite) R.drawable.ic_interest_clicked else R.drawable.ic_interest_unclicked

        binding.btnCafeInterestFavorite.setBackgroundResource(drawableOriginId)

        binding.btnCafeInterestFavorite.setOnClickListener {
            viewModel.dislikeCafe(item.favoritesId)
            val drawableNewId =
                if (item.favorite) R.drawable.ic_interest_clicked else R.drawable.ic_interest_unclicked
            binding.btnCafeInterestFavorite.setBackgroundResource(drawableNewId)
        }
        binding.root.setOnClickListener { recyclerItemListener.onItemSelected(item) }
    }
}
