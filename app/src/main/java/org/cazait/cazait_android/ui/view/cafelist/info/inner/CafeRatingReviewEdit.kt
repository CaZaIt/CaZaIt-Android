package org.cazait.cazait_android.ui.view.cafelist.info.inner

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.ActivityCafeRatingReviewEditBinding
import org.cazait.cazait_android.ui.base.BaseActivity
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

    }
}
