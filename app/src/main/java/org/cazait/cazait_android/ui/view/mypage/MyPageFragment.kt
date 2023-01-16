package org.cazait.cazait_android.ui.view.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.FragmentMyPageBinding
import org.cazait.cazait_android.ui.base.BaseFragment
import org.cazait.cazait_android.ui.base.BaseViewModel

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