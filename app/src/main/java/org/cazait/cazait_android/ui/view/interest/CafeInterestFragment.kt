package org.cazait.cazait_android.ui.view.interest

import MarginItemDecoration
import android.content.Intent
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.CAFE_ITEM_KEY
import org.cazait.cazait_android.R
import org.cazait.cazait_android.data.Resource
import org.cazait.cazait_android.data.model.Cafe
import org.cazait.cazait_android.data.model.Cafes
import org.cazait.cazait_android.data.model.remote.response.InterestCafesResponse
import org.cazait.cazait_android.databinding.FragmentCafeInterestBinding
import org.cazait.cazait_android.ui.adapter.CafeInterestAdapter
import org.cazait.cazait_android.ui.adapter.GridSpacingItemDecoration
import org.cazait.cazait_android.ui.base.BaseFragment
import org.cazait.cazait_android.ui.util.SingleEvent
import org.cazait.cazait_android.ui.util.extension.observe
import org.cazait.cazait_android.ui.util.extension.observeEvent
import org.cazait.cazait_android.ui.util.extension.toGone
import org.cazait.cazait_android.ui.util.extension.toVisible
import org.cazait.cazait_android.ui.view.cafelist.info.CafeInformationActivity
import org.cazait.cazait_android.ui.viewmodel.CafeInterestViewModel
import kotlin.math.roundToInt

@AndroidEntryPoint
class CafeInterestFragment : BaseFragment<FragmentCafeInterestBinding, CafeInterestViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_cafe_interest

    override val viewModel: CafeInterestViewModel by viewModels()
    private lateinit var adapter: CafeInterestAdapter

    override fun initView() {
        setUpRVInterestCafes()
    }

    override fun initAfterBinding() {
        observeViewModel()
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshInterestCafeList()
    }

    private fun observeViewModel() {
        observe(viewModel.interestCafes, ::handleInterestCafes)
        observeEvent(viewModel.openCafeDetails, ::navigateToDetailsScreen)
    }

    private fun handleInterestCafes(status: Resource<InterestCafesResponse>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data.let {
                when (status.data.result) {
                    "SUCCESS" -> {
                        val cafes = convertInterestCafesToCafes(it)
                        if (binding.rvInterestCafes.adapter == null)
                            bindRVInterestCafesData(cafes = cafes)
                        else {
                            updateRVInterestCafesData(cafes = cafes)
                        }
                    }
                    "FAIL" -> {
                        viewModel.refreshInterestCafeList()
                    }
                }
            }
            is Resource.Error -> {
                showDataView(false)
                status.message.let {
                    viewModel.showToastMessage(it)
                }
            }
        }
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.tvNoData.toGone()
        binding.rvInterestCafes.toGone()
    }

    private fun showDataView(isShow: Boolean) {
        binding.tvNoData.visibility = if (isShow) GONE else VISIBLE
        binding.rvInterestCafes.visibility = if (isShow) VISIBLE else GONE
        binding.pbLoading.toGone()
    }

    private fun navigateToDetailsScreen(navigateEvent: SingleEvent<Cafe>) {
        navigateEvent.getContentIfNotHandled().let {
            if (it != null) {
                val nextScreenIntent = Intent(context, CafeInformationActivity::class.java).apply {
                    putExtra(CAFE_ITEM_KEY, it)
                }
                startActivity(nextScreenIntent)
            }
        }
    }

    private fun convertInterestCafesToCafes(interestCafesResponse: InterestCafesResponse): Cafes {
        val cafes = interestCafesResponse.data.map {
            Cafe(
                id = it.cafeId,
                name = it.cafeName,
                distance = 0,
                address = it.address,
                state = it.congestion,
                favorite = true,
                favoritesId = it.favoritesId,
                cafeImageRes = listOf(),
                latitude = it.latitude,
                longitude = it.longitude
            )
        }.toList()
        return Cafes(ArrayList(cafes))
    }

    private fun setUpRVInterestCafes() {
        val spaceDecoration =
            MarginItemDecoration(resources.getDimension(R.dimen.cafe_interest_space).roundToInt())
        val gridSpacingDecoration =
            GridSpacingItemDecoration(resources.getDimension(R.dimen.cafe_interest_grid_space).roundToInt())
        binding.rvInterestCafes.addItemDecoration(spaceDecoration)
        binding.rvInterestCafes.addItemDecoration(gridSpacingDecoration)
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvInterestCafes.layoutManager = layoutManager
    }


    private fun bindRVInterestCafesData(cafes: Cafes) {
        if (cafes.cafesList.isNotEmpty()) {
            adapter = CafeInterestAdapter(viewModel, cafes.cafesList)
            binding.rvInterestCafes.adapter = adapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun updateRVInterestCafesData(cafes: Cafes) {
        (binding.rvInterestCafes.adapter as CafeInterestAdapter).updateData(
            cafes.cafesList
        )
        showDataView(true)
    }
}