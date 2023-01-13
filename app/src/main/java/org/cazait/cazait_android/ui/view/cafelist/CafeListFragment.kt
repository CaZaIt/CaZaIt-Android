package org.cazait.cazait_android.ui.view.cafelist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.FragmentCafeListBinding

class CafeListFragment : Fragment() {
    private lateinit var viewBinding: FragmentCafeListBinding
    private lateinit var viewModel: ViewModel
    private lateinit var dataRVAdapter: CafeListRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())
            .get(ViewModel::class.java)

        viewBinding = FragmentCafeListBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list: ArrayList<CafeData> = viewModel.dataList
        Log.e("FirstFragment", "Data List:$list")

        dataRVAdapter = CafeListRVAdapter(list)
        viewBinding.listView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        viewBinding.listView.adapter = dataRVAdapter
    }
}