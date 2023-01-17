package org.cazait.cazait_android.ui.view.cafelist

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.FragmentCafeListBinding
import org.cazait.cazait_android.ui.base.BaseFragment
import org.cazait.cazait_android.ui.viewmodel.CafeData
import org.cazait.cazait_android.ui.viewmodel.CafeInfoViewModel

class CafeListFragment : BaseFragment<FragmentCafeListBinding, CafeInfoViewModel>() {
    override val viewModel: CafeInfoViewModel by viewModels()
    private lateinit var dataRVAdapter: CafeListRVAdapter

    override val layoutResourceId: Int
        get() = R.layout.fragment_cafe_list

    override fun initView() {
        val list: ArrayList<CafeData> = viewModel.dataList
        Log.e("FirstFragment", "Data List:$list")
    }

    override fun initAfterBinding() {
    }

    override fun initBeforeBinding() {
    }
}