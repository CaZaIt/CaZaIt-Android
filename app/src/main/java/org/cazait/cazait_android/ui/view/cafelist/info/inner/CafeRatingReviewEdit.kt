package org.cazait.cazait_android.ui.view.cafelist.info.inner

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.ActivityCafeRatingReviewEditBinding
import org.cazait.cazait_android.ui.base.BaseActivity
import org.cazait.cazait_android.ui.view.cafelist.info.CafeInformationActivity
import org.cazait.cazait_android.ui.viewmodel.CafeInfoReviewViewModel

class CafeRatingReviewEdit :
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
            Log.d("RatingBar Score","{$rating}점")
        }
        binding.btnReviewOk.setOnClickListener {
            val intent = Intent(this, CafeInformationActivity::class.java)
            Log.d("RatingBar Score","{${binding.ratingBar.rating}}점")
            startActivity(intent)
            finish()
        }
    }
}
