package org.cazait.cazait_android.ui.view.cafelist.info.inner

import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.ActivityCafeRatingReviewEditBinding
import org.cazait.cazait_android.ui.base.BaseActivity
import org.cazait.cazait_android.ui.view.cafelist.info.CafeInformationActivity
import org.cazait.cazait_android.ui.viewmodel.CafeInfoReviewViewModel

@AndroidEntryPoint
class CafeRatingReviewEditActivity :
    BaseActivity<ActivityCafeRatingReviewEditBinding, CafeInfoReviewViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_cafe_rating_review_edit
    override val viewModel: CafeInfoReviewViewModel by viewModels()

    override fun initBeforeBinding() {
    }

    override fun initAfterBinding() {
    }

    override fun initView() {
        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            Log.d("RatingBar Score", "{$rating}점")
        }
        binding.btnReviewOk.setOnClickListener {
            val intent = Intent(this, CafeInformationActivity::class.java)
            Log.d("RatingBar Score", "{${binding.ratingBar.rating}}점")
            Log.d("RatingBar Review", "${binding.etReviewEdit.text}")
            startActivity(intent)
            finish()
        }
        binding.imgbtnReviewBack.setOnClickListener {
            val intent = Intent(this, CafeInformationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
