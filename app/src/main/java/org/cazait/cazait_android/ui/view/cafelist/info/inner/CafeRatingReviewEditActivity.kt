package org.cazait.cazait_android.ui.view.cafelist.info.inner

import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.remote.response.ReviewEditResponse
import org.cazait.cazait_android.databinding.ActivityCafeRatingReviewEditBinding
import org.cazait.cazait_android.ui.base.BaseActivity
import org.cazait.cazait_android.ui.util.extension.observe
import org.cazait.cazait_android.ui.util.extension.toGone
import org.cazait.cazait_android.ui.util.extension.toVisible
import org.cazait.cazait_android.ui.view.cafelist.info.CafeInformationActivity
import org.cazait.cazait_android.ui.viewmodel.CafeInfoReviewEditViewModel

@AndroidEntryPoint
class CafeRatingReviewEditActivity :
    BaseActivity<ActivityCafeRatingReviewEditBinding, CafeInfoReviewEditViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_cafe_rating_review_edit
    override val viewModel: CafeInfoReviewEditViewModel by viewModels()

    override fun initBeforeBinding() {
    }

    override fun initAfterBinding() {
        binding.lifecycleOwner = this
        observeViewModel()
    }

    override fun initView() {
        val cafeId = intent.getLongExtra("cafeId", 0)
        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            Log.d("RatingBar Score", "{$rating}점")
        }
        binding.btnReviewOk.setOnClickListener {
            // 소수점 단위도 가능하기에 서버에 Double 형이나 Float 형으로 변경 요청
            val reviewScore = binding.ratingBar.rating.toInt()
            val reviewText = binding.etReviewEdit.text.toString()
            Log.d("ReviewEdit - score","$reviewScore")
            Log.d("ReviewEdit - text","$reviewText")

            viewModel.editReview(cafeId,reviewText,reviewScore)
            finish()
        }
        binding.imgbtnReviewBack.setOnClickListener {
            finish()
        }
    }

    private fun observeViewModel() {
        observe(viewModel.editProcess, ::handleReviewEditResult)
    }

    private fun handleReviewEditResult(status: Resource<ReviewEditResponse>) {
        when (status) {
            is Resource.Loading -> binding.pbEditLoaderView.toVisible()
            is Resource.Success -> status.data.let {
                binding.pbEditLoaderView.toGone()
                when (status.data.result) {
                    "SUCCESS" -> {
                        val intent = Intent(applicationContext, CafeInformationActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else -> Log.d("ReviewEdit", "리뷰 작성 실패!")
                }
            }
            is Resource.Error -> {
                binding.pbEditLoaderView.toGone()
                Log.d("ReviewEdit", "리뷰 작성 실패!")
            }
        }
    }
}
