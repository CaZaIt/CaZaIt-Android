package org.cazait.cazait_android.ui.view.cafelist.info.inner

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.FragmentCafeRatingReviewBinding
import org.cazait.cazait_android.ui.adapter.CafeInfoReviewAdapter
import org.cazait.cazait_android.ui.base.BaseFragment
import org.cazait.cazait_android.ui.viewmodel.CafeInfoReviewViewModel

@AndroidEntryPoint
class CafeRatingReviewFragment :
    BaseFragment<FragmentCafeRatingReviewBinding, CafeInfoReviewViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_cafe_rating_review

    override val viewModel: CafeInfoReviewViewModel by viewModels()
    private lateinit var reviewAdapter: CafeInfoReviewAdapter

    override fun initAfterBinding() {
        observeReviewListData()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
    }

    override fun initView() {
        initRecyclerView()
        binding.apply {
            swipefreshRatingreview.setOnRefreshListener {
                swipefreshRatingreview.isRefreshing = false
            }
        }
    }

    private fun initRecyclerView() {
        reviewAdapter = CafeInfoReviewAdapter()
        binding.cafeInfoReviewListView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.cafeInfoReviewListView.adapter = reviewAdapter
    }

    private fun observeReviewListData() {
        viewModel.reviewList.observe(this) {
            reviewAdapter.data = it
        }
    }
}