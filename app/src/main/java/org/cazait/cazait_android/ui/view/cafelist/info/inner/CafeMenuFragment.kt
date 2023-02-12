package org.cazait.cazait_android.ui.view.cafelist.info.inner

import MarginItemDecoration
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.CafeMenu
import org.cazait.cazait_android.data.model.CafeMenus
import org.cazait.cazait_android.data.model.remote.response.MenuResponse
import org.cazait.cazait_android.databinding.FragmentCafeMenuBinding
import org.cazait.cazait_android.ui.adapter.CafeInfoMenuAdapter
import org.cazait.cazait_android.ui.base.BaseFragment
import org.cazait.cazait_android.ui.util.extension.observe
import org.cazait.cazait_android.ui.util.extension.toGone
import org.cazait.cazait_android.ui.util.extension.toVisible
import org.cazait.cazait_android.ui.viewmodel.CafeInfoMenuViewModel
import java.util.ArrayList
import kotlin.math.roundToInt

@AndroidEntryPoint
class CafeMenuFragment : BaseFragment<FragmentCafeMenuBinding, CafeInfoMenuViewModel>() {
    override val viewModel: CafeInfoMenuViewModel by viewModels()

    override val layoutResourceId: Int
        get() = R.layout.fragment_cafe_menu

    private lateinit var adapter: CafeInfoMenuAdapter

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
//        observeViewModel()
    }

    override fun initAfterBinding() {
//        observeMenuListData()
        observeViewModel()
    }

    override fun initView() {
        val cafeId = arguments?.getLong("cafeId")
        Log.d("Clicked CafeId Check", "$cafeId")
        if (cafeId != null) {
            viewModel.getMenus(cafeId)
        }

//        initRecyclerView()
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
        Log.d("ResponseData", "$status")
        when (status) {
            is Resource.Loading -> binding.pbMenuLoaderView.toVisible()
            is Resource.Success -> status.data.let {
                binding.pbMenuLoaderView.toGone()
                when (status.data.result) {
                    "SUCCESS" -> {              // 여기서 it은 response
                        Log.d("handleMenuResult", "성공!")
                        Log.d("ResponseData", "${status.data.data}")    // null
                        val menus = convertMenuResponseToCafeMenu(it)
                        bindRVCafeMenuListData(menus = menus)
                    }
                    else -> {
                        Log.d("handleMenuResult", "실패!")
                    }
                }
            }
            is Resource.Error -> {
                Log.d("handleMenuResult", "실패!")
                binding.pbMenuLoaderView.toGone()
            }
        }
    }

    private fun bindRVCafeMenuListData(menus: CafeMenus) {
        val spaceDecoration = MarginItemDecoration(
            resources.getDimension(R.dimen.cafe_info_menu_bottom_space).roundToInt()
        )
        if (menus.menus.isNotEmpty()) {
            adapter = CafeInfoMenuAdapter(viewModel, menus.menus)
            binding.rvCafeInfoMenus.adapter = adapter
            binding.rvCafeInfoMenus.addItemDecoration(spaceDecoration)
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun showDataView(isShow: Boolean) {
        binding.rvCafeInfoMenus.visibility = if (isShow) VISIBLE else GONE
        binding.pbMenuLoaderView.toGone()
    }

    private fun convertMenuResponseToCafeMenu(menuResponse: MenuResponse): CafeMenus {
        val menuList = menuResponse.data.map {
            CafeMenu(
                it.description,
                it.name,
                it.price
            )
        }.toList()
        return CafeMenus(ArrayList(menuList))
    }

//    private fun initRecyclerView() {
//        val spaceDecoration = MarginItemDecoration(
//            resources.getDimension(R.dimen.cafe_info_menu_bottom_space).roundToInt()
//        )
//        val recyclerView = binding.rvCafeInfoMenus
//        recyclerView.layoutManager =
//            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
//        recyclerView.addItemDecoration(spaceDecoration)
//        recyclerView.adapter = menuAdapter
//    }

    private fun observeMenuListData() {
        viewModel.cafeMenuList.observe(this) {
//            menuAdapter.data = it
        }
    }
}
