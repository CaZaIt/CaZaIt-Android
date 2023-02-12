package org.cazait.cazait_android.ui.view.cafelist.info.inner

import MarginItemDecoration
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.ReviewData
import org.cazait.cazait_android.data.model.ReviewDatas
import org.cazait.cazait_android.data.model.remote.response.ReviewResponse
import org.cazait.cazait_android.databinding.FragmentCafeRatingReviewBinding
import org.cazait.cazait_android.ui.adapter.CafeInfoReviewAdapter
import org.cazait.cazait_android.ui.base.BaseFragment
import org.cazait.cazait_android.ui.util.extension.observe
import org.cazait.cazait_android.ui.util.extension.toGone
import org.cazait.cazait_android.ui.util.extension.toVisible
import org.cazait.cazait_android.ui.viewmodel.CafeInfoReviewViewModel
import kotlin.math.roundToInt

@AndroidEntryPoint
class CafeRatingReviewFragment :
    BaseFragment<FragmentCafeRatingReviewBinding, CafeInfoReviewViewModel>() {
    override val viewModel: CafeInfoReviewViewModel by viewModels()

    override val layoutResourceId: Int
        get() = R.layout.fragment_cafe_rating_review

    private lateinit var adapter: CafeInfoReviewAdapter

    override fun initAfterBinding() {
        observeViewModel()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
    }

    override fun initView() {
        val cafeId = arguments?.getLong("cafeId")
        Log.d("Clicked CafeId Check", "$cafeId")
        if (cafeId != null) {
            viewModel.getReviews(cafeId)
        }
        binding.apply {
            swipefreshRatingreview.setOnRefreshListener {
                swipefreshRatingreview.isRefreshing = false
            }
        }
    }

    private fun observeViewModel() {
        observe(viewModel.reviewList, ::handleReviewResult)
    }

    private fun handleReviewResult(status: Resource<ReviewResponse>) {
        Log.d("ResponseData - Review - status", "$status")
        when (status) {
            is Resource.Loading -> binding.pbReviewLoaderView.toVisible()
            is Resource.Success -> status.data.let {
                binding.pbReviewLoaderView.toGone()
                when (status.data.result) {
                    "SUCCESS" -> {
                        Log.d("handleReviewResult", "성공!")
                        Log.d("ResponseData - Review", "${status.data.data}")
                        val reviews = convertReviewResponseToReviewData(it)
                        Log.d("convert complete?", "넘어왔나?")
                        bindRVReviewDataListData(reviews = reviews)
                        Log.d("convert complete?", "그럼 여기는?")

                    }
                    else -> {
                        Log.d("handleReviewResult", "실패!")
                    }
                }
            }
            is Resource.Error -> {
                Log.d("handleReviewResult", "실패!")
                binding.pbReviewLoaderView.toGone()
            }
        }
    }

    private fun bindRVReviewDataListData(reviews: ReviewDatas) {
        val spaceDecoration = MarginItemDecoration(
            resources.getDimension(R.dimen.cafe_info_menu_bottom_space).roundToInt()
        )
        if (reviews.reviews.isNotEmpty()) {
            adapter = CafeInfoReviewAdapter(viewModel, reviews.reviews)
            binding.cafeInfoReviewListView.adapter = adapter
            binding.cafeInfoReviewListView.addItemDecoration(spaceDecoration)
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun showDataView(isShow: Boolean) {
        binding.cafeInfoReviewListView.visibility = if (isShow) VISIBLE else GONE
        binding.pbReviewLoaderView.toGone()
    }

    private fun convertReviewResponseToReviewData(reviewResponse: ReviewResponse): ReviewDatas {
        val reviewList = reviewResponse.data.map {
            ReviewData(
                it.score,
                "${R.string.view_more_title}",
                it.userId.toString(),
                "${R.string.view_more_notification}",
                it.content
            )
        }.toList()
        return ReviewDatas(ArrayList(reviewList))
    }

//    private fun initRecyclerView() {
//        reviewAdapter = CafeInfoReviewAdapter()
//        binding.cafeInfoReviewListView.layoutManager =
//            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
//        binding.cafeInfoReviewListView.adapter = reviewAdapter
//    }
//
//    private fun observeReviewListData() {
//        viewModel.reviewList.observe(this) {
//            reviewAdapter.data = it
//        }
//    }
}