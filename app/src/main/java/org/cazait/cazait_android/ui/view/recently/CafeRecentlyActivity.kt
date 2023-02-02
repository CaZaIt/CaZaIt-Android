package org.cazait.cazait_android.ui.view.recently

import MarginItemDecoration
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
import org.cazait.cazait_android.data.Datasource
import org.cazait.cazait_android.databinding.ActivityCafeRecentlyBinding
import org.cazait.cazait_android.ui.adapter.CafeRecentlyAdapter
import org.cazait.cazait_android.ui.base.BaseActivity
import org.cazait.cazait_android.ui.viewmodel.CafeRecentlyViewModel
import kotlin.math.roundToInt

@AndroidEntryPoint
class CafeRecentlyActivity : BaseActivity<ActivityCafeRecentlyBinding, CafeRecentlyViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_cafe_recently

    override val viewModel: CafeRecentlyViewModel by viewModels()
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

        val dataset = Datasource().loadAffirmations()
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

