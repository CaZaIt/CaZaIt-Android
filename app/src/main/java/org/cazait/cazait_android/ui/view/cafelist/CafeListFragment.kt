package org.cazait.cazait_android.ui.view.cafelist

import MarginItemDecoration
import android.util.SparseBooleanArray
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
import org.cazait.cazait_android.data.Datasource
import org.cazait.cazait_android.data.model.CafeState
import org.cazait.cazait_android.databinding.FragmentCafeListBinding
import org.cazait.cazait_android.ui.adapter.CafeListItemAdapter
import org.cazait.cazait_android.ui.base.BaseFragment
import org.cazait.cazait_android.ui.viewmodel.CafeListViewModel
import kotlin.math.roundToInt

@AndroidEntryPoint
class CafeListFragment : BaseFragment<FragmentCafeListBinding, CafeListViewModel>() {
    override val viewModel: CafeListViewModel by viewModels()

    override val layoutResourceId: Int
        get() = R.layout.fragment_cafe_list

    private val adapter: CafeListItemAdapter = CafeListItemAdapter()
    override fun initBeforeBinding() {
        // 뷰모델을 lifeCyle 에 종속시킨다. lifeCycle 동안 옵저버 역할을 하게 된다.
        binding.lifecycleOwner = this
    }

    override fun initView() {
        initRecyclerView()
    }

    override fun initAfterBinding() {
        observeCafeListData()
        setOnclickListener()
    }

    private fun initRecyclerView() {
        val dataset = Datasource().loadAffirmations()
        val spaceDecoration =
            MarginItemDecoration(resources.getDimension(R.dimen.cafe_item_space).roundToInt())
        val recyclerView = binding.rvCafeList

        recyclerView.addItemDecoration(spaceDecoration)
        recyclerView.adapter = adapter
    }

    private fun observeCafeListData() {
        viewModel.cafeStateList.observe(this) {
            adapter.data = it
        }
    }

    private fun setOnclickListener() {
        binding.imgBtnMenu.setOnClickListener {
            viewModel.addItem()
        }
    }
}