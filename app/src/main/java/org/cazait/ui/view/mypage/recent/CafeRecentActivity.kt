package org.cazait.ui.view.mypage.recent

import MarginItemDecoration
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.R
import org.cazait.databinding.ActivityCafeRecentBinding
import org.cazait.ui.adapter.CafeRecentlyAdapter
import org.cazait.ui.base.BaseActivity
import org.cazait.ui.viewmodel.CafeRecentViewModel
import kotlin.math.roundToInt

@AndroidEntryPoint
class CafeRecentActivity : BaseActivity<ActivityCafeRecentBinding, CafeRecentViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_cafe_recent

    override val viewModel: CafeRecentViewModel by viewModels()
    private lateinit var recentlyAdapter: CafeRecentlyAdapter

    override fun initBeforeBinding() {
    }

    override fun initView() {
        initRecyclerView()
    }

    override fun initAfterBinding() {
        observeRecentlyListData()
    }

    private fun initRecyclerView() {
        recentlyAdapter = CafeRecentlyAdapter()
        binding.rvCafeRecently.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvCafeRecently.adapter = recentlyAdapter

        val spaceDecoration =
            MarginItemDecoration(resources.getDimension(R.dimen.cafe_item_space).roundToInt())
        val recyclerView = binding.rvCafeRecently

        recyclerView.addItemDecoration(spaceDecoration)
    }

    private fun observeRecentlyListData() {
        viewModel.cafeRecentlyList.observe(this) {
            recentlyAdapter.recently = it
        }
    }
}
