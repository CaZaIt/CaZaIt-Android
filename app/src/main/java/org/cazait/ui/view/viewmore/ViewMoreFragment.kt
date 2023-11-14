package org.cazait.ui.view.viewmore

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.R
import org.cazait.databinding.FragmentViewMoreBinding
import org.cazait.ui.base.BaseFragment
import org.cazait.ui.base.BaseViewModel

@AndroidEntryPoint
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
