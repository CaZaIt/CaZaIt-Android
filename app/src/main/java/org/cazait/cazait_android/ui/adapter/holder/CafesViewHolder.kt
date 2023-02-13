package org.cazait.cazait_android.ui.adapter.holder

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.cazait.cazait_android.data.model.Cafe
import org.cazait.cazait_android.databinding.ItemCafeInterestBinding
import org.cazait.cazait_android.databinding.ItemCafeMainBinding
import org.cazait.cazait_android.ui.base.RecyclerItemListener
import org.cazait.cazait_android.ui.viewmodel.CafeInterestViewModel
import org.cazait.cazait_android.ui.viewmodel.CafeListViewModel

class CafesViewHolder(
    val binding: ViewDataBinding,
    val viewModel: ViewModel? = null
) : RecyclerView.ViewHolder(binding.root) {
    fun bindForCafeListPage(item: Cafe, recyclerItemListener: RecyclerItemListener) {
        (binding as ItemCafeMainBinding).cafe = item

        val imgUrl = item.cafeImageRes[0].imageUrl
        Glide.with(itemView).load(imgUrl).into(binding.ivCafeLandscape)

        binding.btnCafeMainFavorite.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                (viewModel as CafeListViewModel).likeCafe(
                    userId = viewModel.userIdLiveData.value!!,
                    cafeId = item.id
                )
            }
        }
        binding.root.setOnClickListener { recyclerItemListener.onItemSelected(item) }
    }

    fun bindForInterestPage(item: Cafe, recyclerItemListener: RecyclerItemListener) {
        (binding as ItemCafeInterestBinding).cafe = item

        val imgUrl = item.cafeImageRes[0].imageUrl
        Glide.with(itemView).load(imgUrl).into(binding.ivCafeInterestLandscape)

        binding.btnCafeInterestFavorite.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                (viewModel as CafeInterestViewModel).dislikeCafe(item.favoritesId!!)
            }
        }
        binding.root.setOnClickListener { recyclerItemListener.onItemSelected(item) }
    }
}