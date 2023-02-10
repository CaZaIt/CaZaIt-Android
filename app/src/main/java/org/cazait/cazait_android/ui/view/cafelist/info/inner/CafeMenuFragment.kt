package org.cazait.cazait_android.ui.view.cafelist.info.inner

import MarginItemDecoration
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.remote.response.MenuResponse
import org.cazait.cazait_android.databinding.FragmentCafeMenuBinding
import org.cazait.cazait_android.ui.adapter.CafeInfoMenuAdapter
import org.cazait.cazait_android.ui.base.BaseFragment
import org.cazait.cazait_android.ui.util.extension.observe
import org.cazait.cazait_android.ui.util.extension.toGone
import org.cazait.cazait_android.ui.util.extension.toVisible
import org.cazait.cazait_android.ui.viewmodel.CafeInfoMenuViewModel
import kotlin.math.roundToInt

@AndroidEntryPoint
class CafeMenuFragment : BaseFragment<FragmentCafeMenuBinding, CafeInfoMenuViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_cafe_menu

    override val viewModel: CafeInfoMenuViewModel by viewModels()
    private val menuAdapter = CafeInfoMenuAdapter()

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
        observeViewModel()
    }

    override fun initAfterBinding() {
        observeMenuListData()
    }

    override fun initView() {
        initRecyclerView()
        binding.apply {
            swipefreshMenu.setOnRefreshListener {
                swipefreshMenu.isRefreshing = false
            }
        }
    }

    private fun observeViewModel() {
        observe(viewModel.cafeMenuList, ::handleMenuResult)
    }

    private fun handleMenuResult(status: Resource<MenuResponse>) {
        when (status) {
            is Resource.Loading -> binding.pbMenuLoaderView.toVisible()
            is Resource.Success -> status.data.let {
                binding.pbMenuLoaderView.toGone()
                when (status.data.result) {
                    "SUCCESS" -> {
                        Log.d("handleMenuResult","성공!")
                    }
                    else -> {Log.d("handleMenuResult","실패!")}
                }
            }
            is Resource.Error -> {
                binding.pbMenuLoaderView.toGone()
            }
        }
    }

    private fun initRecyclerView() {
        val spaceDecoration = MarginItemDecoration(
            resources.getDimension(R.dimen.cafe_info_menu_bottom_space).roundToInt()
        )
        val recyclerView = binding.rvCafeInfoMenus
        recyclerView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerView.addItemDecoration(spaceDecoration)
        recyclerView.adapter = menuAdapter
    }

    private fun observeMenuListData() {
        viewModel.cafeMenuList.observe(this) {
            menuAdapter.data = it
        }
    }
}