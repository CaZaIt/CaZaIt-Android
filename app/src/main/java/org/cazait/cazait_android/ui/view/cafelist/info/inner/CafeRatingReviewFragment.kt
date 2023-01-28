package org.cazait.cazait_android.ui.view.cafelist.info.inner

import android.app.AlertDialog
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.FragmentCafeRatingReviewBinding
import org.cazait.cazait_android.ui.adapter.CafeInfoReviewRVAdapter
import org.cazait.cazait_android.ui.base.BaseFragment
import org.cazait.cazait_android.ui.viewmodel.CafeInfoReviewViewModel
import org.cazait.cazait_android.ui.viewmodel.ReviewData

@AndroidEntryPoint
class CafeRatingReviewFragment :
    BaseFragment<FragmentCafeRatingReviewBinding, CafeInfoReviewViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_cafe_rating_review

    override val viewModel: CafeInfoReviewViewModel by viewModels()
    private lateinit var reviewRVAdapter: CafeInfoReviewRVAdapter

    override fun initAfterBinding() {
    }

    override fun initBeforeBinding() {
    }

    override fun initView() {
        val list: ArrayList<ReviewData> = viewModel.reviewList

        reviewRVAdapter = CafeInfoReviewRVAdapter(list)
        binding.cafeInfoReviewListView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.cafeInfoReviewListView.adapter = reviewRVAdapter

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
}