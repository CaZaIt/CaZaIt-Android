package org.cazait.ui.view.interest

import MarginItemDecoration
import android.content.Intent
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.CAFE_ITEM_KEY
import org.cazait.R
import org.cazait.databinding.FragmentCafeInterestBinding
import org.cazait.data.Resource
import org.cazait.data.model.Cafe
import org.cazait.data.model.CafeImageRes
import org.cazait.data.model.Cafes
import org.cazait.data.model.remote.response.InterestCafesResponse
import org.cazait.ui.adapter.CafeInterestAdapter
import org.cazait.ui.adapter.GridSpacingItemDecoration
import org.cazait.ui.base.BaseFragment
import org.cazait.ui.util.SingleEvent
import org.cazait.ui.util.extension.observe
import org.cazait.ui.util.extension.observeEvent
import org.cazait.ui.util.extension.toGone
import org.cazait.ui.util.extension.toVisible
import org.cazait.ui.view.cafelist.info.CafeInformationActivity
import org.cazait.ui.viewmodel.CafeInterestViewModel
import kotlin.math.roundToInt

@AndroidEntryPoint
class CafeInterestFragment : BaseFragment<FragmentCafeInterestBinding, CafeInterestViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_cafe_interest

    override lateinit var viewModel: CafeInterestViewModel

    private lateinit var adapter: CafeInterestAdapter

    override fun initView() {
        viewModel = ViewModelProvider(this)[CafeInterestViewModel::class.java]
        setUpRVInterestCafes()
    }

    override fun initAfterBinding() {
        observeViewModel()
    }

    override fun initBeforeBinding() {}

    override fun onResume() {
        super.onResume()
        binding.lifecycleOwner = this
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
                        if (binding.rvInterestCafes.adapter == null) {
                            bindRVInterestCafesData(cafes = cafes)
                        } else {
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
                cafeImageRes = if (it.imageUrl.isNotEmpty()) {
                    listOf(
                        CafeImageRes(
                            0,
                            it.imageUrl[0],
                        ),
                    )
                } else {
                    listOf()
                },
                latitude = it.latitude,
                longitude = it.longitude,
            )
        }.toList()
        return Cafes(ArrayList(cafes))
    }

    private fun setUpRVInterestCafes() {
        val spaceDecoration =
            MarginItemDecoration(resources.getDimension(R.dimen.cafe_interest_space).roundToInt())
        val gridSpacingDecoration =
            GridSpacingItemDecoration(
                resources.getDimension(R.dimen.cafe_interest_grid_space).roundToInt(),
            )
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
            cafes.cafesList,
        )
        showDataView(true)
    }
}
