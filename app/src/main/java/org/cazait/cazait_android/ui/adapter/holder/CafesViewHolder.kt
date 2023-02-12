package org.cazait.cazait_android.ui.adapter.holder

import android.util.Log
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.data.model.Cafe
import org.cazait.cazait_android.data.model.remote.response.InterestCafesResponse
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
        binding.btnCafeMainFavorite.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Log.d("Button", "checked")
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
        binding.btnCafeInterestFavorite.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                (viewModel as CafeInterestViewModel).dislikeCafe(item.favoritesId!!)
            }
        }
        binding.root.setOnClickListener { recyclerItemListener.onItemSelected(item) }
    }
}