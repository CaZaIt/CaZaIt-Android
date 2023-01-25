package org.cazait.cazait_android.ui.view.cafelist.info.inner

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.FragmentCafeMenuBinding
import org.cazait.cazait_android.ui.adapter.CafeInfoMenuRVAdapter
import org.cazait.cazait_android.ui.base.BaseFragment
import org.cazait.cazait_android.ui.viewmodel.CafeInfoMenuViewModel
import org.cazait.cazait_android.ui.viewmodel.MenuData

@AndroidEntryPoint
class CafeMenuFragment : BaseFragment<FragmentCafeMenuBinding, CafeInfoMenuViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_cafe_menu

    override val viewModel: CafeInfoMenuViewModel by viewModels()
    private lateinit var menuRVAdapter: CafeInfoMenuRVAdapter

    override fun initAfterBinding() {
    }

    override fun initBeforeBinding() {
    }

    override fun initView() {
        val list: ArrayList<MenuData> = viewModel.menuList
        menuRVAdapter = CafeInfoMenuRVAdapter(list)
        binding.cafeInfoListView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.cafeInfoListView.adapter = menuRVAdapter
    }
}