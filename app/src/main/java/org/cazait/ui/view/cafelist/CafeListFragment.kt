package org.cazait.ui.view.cafelist

import MarginItemDecoration
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.CAFE_ITEM_KEY
import org.cazait.R
import org.cazait.databinding.FragmentCafeListBinding
import org.cazait.data.Resource
import org.cazait.data.model.Cafe
import org.cazait.data.model.Cafes
import org.cazait.data.model.remote.response.CafeListResponse
import org.cazait.ui.adapter.CafeListItemAdapter
import org.cazait.ui.base.BaseFragment
import org.cazait.ui.util.SingleEvent
import org.cazait.ui.util.extension.observe
import org.cazait.ui.util.extension.observeEvent
import org.cazait.ui.util.extension.toGone
import org.cazait.ui.util.extension.toVisible
import org.cazait.ui.view.cafelist.info.CafeInformationActivity
import org.cazait.ui.viewmodel.CafeListViewModel
import kotlin.math.*

@AndroidEntryPoint
class CafeListFragment : BaseFragment<FragmentCafeListBinding, CafeListViewModel>() {

    override lateinit var viewModel: CafeListViewModel

    override val layoutResourceId: Int
        get() = R.layout.fragment_cafe_list

    private lateinit var adapter: CafeListItemAdapter

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            handleLocationPermissionResult(isGranted)
        }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
    }

    override fun initView() {
        viewModel = ViewModelProvider(this)[CafeListViewModel::class.java]
        getUserLocation()
    }

    override fun initAfterBinding() {
        viewModel.refreshCafeList()
        observeViewModel()
    }

    private fun observeViewModel() {
        observe(viewModel.cafesLiveData, ::handleCafeList)
        observeEvent(viewModel.userLocation, ::handleUserLocation)
        observeEvent(viewModel.openCafeDetails, ::navigateToDetailsScreen)
    }

    private fun getUserLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION,
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            viewModel.getUserLocation()
        }
    }

    private fun handleLocationPermissionResult(isGranted: Boolean) {
        if (isGranted) {
            viewModel.getUserLocation()
        } else {
            // Permission denied, show a message to the user
            viewModel.showToastMessage("카페 목록을 불러오기 위해 위치 접근 권한이 필요합니다")
        }
    }

    private fun handleCafeList(status: Resource<CafeListResponse>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data.let {
                Log.d("CafeListFragment", "${status.data.message} ${status.data.result}")
                when (status.data.result) {
                    "SUCCESS" -> {
                        val cafes = convertCafeListResponseToCafes(it)
                        bindRVCafeListData(cafes = cafes)
                    }

                    "FAIL" -> {
                        viewModel.refreshCafeList()
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

    private fun bindRVCafeListData(cafes: Cafes) {
        val spaceDecoration =
            MarginItemDecoration(resources.getDimension(R.dimen.cafe_item_space).roundToInt())

        if (cafes.cafesList.isNotEmpty()) {
            adapter = CafeListItemAdapter(viewModel, cafes.cafesList)
            binding.rvCafeList.adapter = adapter
            binding.rvCafeList.addItemDecoration(spaceDecoration)
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.tvNoData.toGone()
        binding.rvCafeList.toGone()
    }

    private fun showDataView(isShow: Boolean) {
        binding.tvNoData.visibility = if (isShow) GONE else VISIBLE
        binding.rvCafeList.visibility = if (isShow) VISIBLE else GONE
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

    private fun handleUserLocation(locationEvent: SingleEvent<Location>) {
        locationEvent.getContentIfNotHandled().let {
            viewModel.refreshCafeList()
        }
    }

    private fun convertCafeListResponseToCafes(cafeListResponse: CafeListResponse): Cafes {
        // data[0]인 이유는 0번째 페이지이기 때문임 만일 페이지 수가 넘어가면 1씩 증가시켜서 추가해줘야 함

        val cafeList = cafeListResponse.data[0].map {
            Cafe(
                id = it.cafeId,
                name = it.name,
                distance = 147,
                address = it.address,
                state = it.congestionStatus,
                favorite = it.favorite,
                cafeImageRes = it.cafeImageRes,
                latitude = it.latitude,
                longitude = it.longitude,
            )
        }.toList()
        return Cafes(ArrayList(cafeList))
    }

    private fun getDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Int {
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2).pow(2.0) + sin(dLon / 2).pow(2.0) * cos(Math.toRadians(lat1)) * cos(
            Math.toRadians(lat2),
        )
        val c = 2 * asin(sqrt(a))
        return (a * c).toInt()
    }
}
