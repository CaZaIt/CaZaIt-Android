package org.cazait.cazait_android.ui.view.cafelist.info.inner

import android.app.AlertDialog
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.FragmentCafeRatingReviewBinding
import org.cazait.cazait_android.ui.adapter.CafeInfoReviewAdapter
import org.cazait.cazait_android.ui.base.BaseFragment
import org.cazait.cazait_android.ui.viewmodel.CafeInfoReviewViewModel

@AndroidEntryPoint
class CafeRatingReviewFragment :
    BaseFragment<FragmentCafeRatingReviewBinding, CafeInfoReviewViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_cafe_rating_review

    override val viewModel: CafeInfoReviewViewModel by viewModels()
    private lateinit var reviewAdapter: CafeInfoReviewAdapter

    override fun initAfterBinding() {
        observeReviewListData()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
    }

    override fun initView() {
        initRecyclerView()

        binding.tvFilter.setOnClickListener {
            showDialog()
        }
        binding.imgbtnFilter.setOnClickListener {
            showDialog()
        }
    }

    // 정렬 기준 선택 다이얼로그
    private fun showDialog(){
        val sortMenu:Array<String> = resources.getStringArray(R.array.sortmenu)
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("정렬 메뉴")
        builder.setItems(sortMenu){ p0, p1 ->
            Toast.makeText(context, "선택된 정렬 기준은 ${sortMenu[p1]}", Toast.LENGTH_LONG).show()
        }
        val alertDialog:AlertDialog = builder.create()
        alertDialog.show()
    }

    private fun initRecyclerView() {
        reviewAdapter = CafeInfoReviewAdapter()
        binding.cafeInfoReviewListView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.cafeInfoReviewListView.adapter = reviewAdapter
    }

    private fun observeReviewListData() {
        viewModel.reviewList.observe(this){
            reviewAdapter.data = it
        }
    }
}