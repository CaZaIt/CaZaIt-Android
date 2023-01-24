package org.cazait.cazait_android.ui.view.cafelist.info

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.ActivityCafeInformationBinding
import org.cazait.cazait_android.ui.base.BaseActivity
import org.cazait.cazait_android.ui.viewmodel.CafeInfoViewModel

@AndroidEntryPoint
class CafeInformationActivity : BaseActivity<ActivityCafeInformationBinding, CafeInfoViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_cafe_information

    override val viewModel: CafeInfoViewModel by viewModels()

    override fun initAfterBinding() {

    }

    override fun initBeforeBinding() {
    }

    override fun initView() {

    }
}