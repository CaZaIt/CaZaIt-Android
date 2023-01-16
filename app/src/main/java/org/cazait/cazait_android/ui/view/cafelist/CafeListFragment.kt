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

        dataRVAdapter = CafeListRVAdapter(list)
        binding.listView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.listView.adapter = dataRVAdapter
    }

    override fun initAfterBinding() {
    }

    override fun initBeforeBinding() {
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())
//            .get(ViewModel::class.java)
//
//        viewBinding = FragmentCafeListBinding.inflate(inflater, container, false)
//        return viewBinding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val list: ArrayList<CafeData> = viewModel.dataList
//        Log.e("FirstFragment", "Data List:$list")
//
//        dataRVAdapter = CafeListRVAdapter(list)
//        viewBinding.listView.layoutManager =
//            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
//        viewBinding.listView.adapter = dataRVAdapter
//    }
}