package org.cazait.cazait_android.ui.view.viewmore

import androidx.fragment.app.viewModels
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.FragmentViewMoreBinding
import org.cazait.cazait_android.ui.base.BaseFragment
import org.cazait.cazait_android.ui.base.BaseViewModel

class ViewMoreFragment : BaseFragment<FragmentViewMoreBinding, BaseViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_view_more

    override val viewModel: BaseViewModel by viewModels()

    override fun initAfterBinding() {

    }

    override fun initBeforeBinding() {

    }

    override fun initView() {

    }
}