package org.cazait.cazait_android.ui.view.interest

import MarginItemDecoration
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.FragmentCafeInterestBinding
import org.cazait.cazait_android.ui.adapter.CafeInterestAdapter
import org.cazait.cazait_android.ui.base.BaseFragment
import org.cazait.cazait_android.ui.viewmodel.CafeInterestViewModel
import org.cazait.cazait_android.ui.viewmodel.CafeInterestViewModel
import org.cazait.cazait_android.R
import org.cazait.cazait_android.ui.adapter.CafeInterestAdapter
import org.cazait.cazait_android.ui.base.BaseFragment
import kotlin.math.roundToInt

@AndroidEntryPoint
class CafeInterestFragment : BaseFragment<FragmentCafeInterestBinding, CafeInterestViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_cafe_interest

    override val viewModel: CafeInterestViewModel by viewModels()
    private val interestAdapter = CafeInterestAdapter()


    override fun initView() {
        initRecyclerView()

    }

    override fun initAfterBinding() {
        observeInterestListData()
    }

    override fun initBeforeBinding() {
    }

    private fun initRecyclerView() {

        val recyclerView = binding.rvCafeInterest
        recyclerView.layoutManager =
            GridLayoutManager(activity, 2)
        recyclerView.adapter = interestAdapter

        val spaceDecoration =
            MarginItemDecoration(resources.getDimension(R.dimen.cafe_interest_space).roundToInt())
        recyclerView.addItemDecoration(spaceDecoration)
    }

    private fun observeInterestListData() {
        viewModel.cafeInterestList.observe(this) {
            interestAdapter.interest = it
        }
    }
}