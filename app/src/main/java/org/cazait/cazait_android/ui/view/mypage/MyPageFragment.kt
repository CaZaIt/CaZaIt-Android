package org.cazait.cazait_android.ui.view.mypage


import android.content.Intent
import android.text.TextUtils.replace
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.CafeInterestFragment
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.FragmentMyPageBinding
import org.cazait.cazait_android.ui.base.BaseFragment
import org.cazait.cazait_android.ui.base.BaseViewModel
import org.cazait.cazait_android.ui.util.extension.replace


@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding, BaseViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page

    override val viewModel: BaseViewModel by viewModels()

    override fun initAfterBinding() {

    }

    override fun initBeforeBinding() {

    }


    override fun initView() {

    }
}


