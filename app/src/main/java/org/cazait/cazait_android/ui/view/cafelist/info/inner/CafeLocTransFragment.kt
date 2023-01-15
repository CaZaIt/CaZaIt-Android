package org.cazait.cazait_android.ui.view.cafelist.info.inner

import androidx.fragment.app.viewModels
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.FragmentCafeLocTransBinding
import org.cazait.cazait_android.ui.base.BaseFragment
import org.cazait.cazait_android.ui.viewmodel.CafeInfoViewModel

class CafeLocTransFragment : BaseFragment<FragmentCafeLocTransBinding, CafeInfoViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_cafe_loc_trans

    override val viewModel: CafeInfoViewModel by viewModels()

    override fun initAfterBinding() {
    }

    override fun initBeforeBinding() {
    }

    override fun initView() {
    }

}