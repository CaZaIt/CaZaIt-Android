package org.cazait.ui.view.cafelist.info.inner

import MarginItemDecoration
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.R
import org.cazait.databinding.FragmentCafeMenuBinding
import org.cazait.data.Resource
import org.cazait.data.model.CafeMenu
import org.cazait.data.model.CafeMenus
import org.cazait.data.model.remote.response.MenuResponse
import org.cazait.ui.adapter.CafeInfoMenuAdapter
import org.cazait.ui.base.BaseFragment
import org.cazait.ui.util.extension.observe
import org.cazait.ui.util.extension.toGone
import org.cazait.ui.util.extension.toVisible
import org.cazait.ui.viewmodel.CafeInfoMenuViewModel
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
                    "SUCCESS" -> { // 여기서 it은 response
                        Log.d("handleMenuResult", "성공!")
                        Log.d("ResponseData", "${status.data.data}") // null
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
            resources.getDimension(R.dimen.cafe_info_menu_bottom_space).roundToInt(),
        )
        if (menus.menus.isNotEmpty()) {
            adapter = CafeInfoMenuAdapter(this, viewModel, menus.menus)
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
                it.price,
                it.imageUrl,
            )
        }.toList()
        return CafeMenus(ArrayList(menuList))
    }
}
