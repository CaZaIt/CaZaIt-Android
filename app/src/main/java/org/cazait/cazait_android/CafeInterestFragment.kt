package org.cazait.cazait_android


import androidx.fragment.app.viewModels
import org.cazait.cazait_android.data.Datasource
import org.cazait.cazait_android.databinding.FragmentCafeInterestBinding
import org.cazait.cazait_android.ui.base.BaseFragment
import org.cazait.cazait_android.ui.viewmodel.CafeInfoViewModel

class CafeInterestFragment : BaseFragment<FragmentCafeInterestBinding, CafeInfoViewModel>() {

    override val viewModel: CafeInfoViewModel by viewModels()

    override val layoutResourceId: Int
        get() = R.layout.fragment_cafe_interest

    override fun initView() {
        initRecyclerView()

    }

    override fun initAfterBinding() {

    }

    override fun initBeforeBinding() {
    }

    private fun initRecyclerView() {
        val dataset = Datasource().loadAffirmations()
        val recyclerView = binding.rvCafeInterest
        recyclerView.adapter = CafeInterestRVAdapter(requireContext(), dataset)
    }
}


