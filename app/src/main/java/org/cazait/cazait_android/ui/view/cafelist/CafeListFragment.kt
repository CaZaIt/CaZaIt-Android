package org.cazait.cazait_android.ui.view.cafelist

import MarginItemDecoration
import android.util.Log
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
import org.cazait.cazait_android.data.Datasource
import org.cazait.cazait_android.databinding.FragmentCafeListBinding
import org.cazait.cazait_android.ui.adapter.CafeListItemAdapter
import org.cazait.cazait_android.ui.base.BaseFragment
import org.cazait.cazait_android.ui.viewmodel.CafeInfoViewModel
import kotlin.math.roundToInt

@AndroidEntryPoint
class CafeListFragment : BaseFragment<FragmentCafeListBinding, CafeInfoViewModel>() {
    override val viewModel: CafeInfoViewModel by viewModels()

    override val layoutResourceId: Int
        get() = R.layout.fragment_cafe_list

    override fun initView() {
        initRecyclerView()

    }

    override fun initAfterBinding() {
    }

    override fun initBeforeBinding() {
    }

    private fun initRecyclerView() {
        val dataset = Datasource().loadAffirmations()
        val spaceDecoration = MarginItemDecoration(resources.getDimension(R.dimen.cafe_item_space).roundToInt())
        val recyclerView = binding.rvCafeList

        recyclerView.addItemDecoration(spaceDecoration)
        recyclerView.adapter = CafeListItemAdapter(requireContext(), dataset)
    }
}