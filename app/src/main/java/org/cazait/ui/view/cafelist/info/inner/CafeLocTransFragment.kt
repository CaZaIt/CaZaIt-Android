package org.cazait.ui.view.cafelist.info.inner

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.R
import org.cazait.REQUEST_LOCATION_PERMISSION
import org.cazait.databinding.FragmentCafeLocTransBinding
import org.cazait.ui.base.BaseFragment
import org.cazait.ui.view.cafelist.info.util.OnMapTouchListener
import org.cazait.ui.view.cafelist.info.util.TouchableWrapper
import org.cazait.ui.viewmodel.CafeInfoLocTransViewModel

@AndroidEntryPoint
class CafeLocTransFragment :
    BaseFragment<FragmentCafeLocTransBinding, CafeInfoLocTransViewModel>(),
    OnMapReadyCallback {
    override val layoutResourceId: Int
        get() = R.layout.fragment_cafe_loc_trans

    override val viewModel: CafeInfoLocTransViewModel by viewModels()
    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource
    private var listener: OnMapTouchListener? = null
    private var cafeLat: Double = 0.0
    private var cafeLong: Double = 0.0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = requireActivity() as? OnMapTouchListener
    }

    override fun initAfterBinding() {
    }

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
    }

    override fun initView() {
        cafeLat = arguments?.getString("cafeLat")!!.toDouble()
        cafeLong = arguments?.getString("cafeLong")!!.toDouble()
        Log.d("Clicked CafeLat Check", "$cafeLat")
        Log.d("Clicked CafeLong Check", "$cafeLong")
        createMap()
        locationSource = FusedLocationSource(this, REQUEST_LOCATION_PERMISSION)
        onMapTouch()
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        naverMap.locationSource = locationSource
        naverMap.uiSettings.isLocationButtonEnabled = true

        showLocationRange()
    }

    private fun createMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.naver_map) as MapFragment?
            ?: MapFragment.newInstance().also {
                childFragmentManager.beginTransaction().add(R.id.naver_map, it).commit()
            }
        mapFragment.getMapAsync(this)
    }

    private fun showLocationRange() {
        // 지정한 위치로 카메라 이동
        val cameraUpdate = CameraUpdate.scrollTo(LatLng(cafeLat, cafeLong))
            .animate(CameraAnimation.Easing, 1000)
        naverMap.moveCamera(cameraUpdate)

        // 지정한 위치에 마커 적용
        Marker().apply {
            position = LatLng(cafeLat, cafeLong)
            map = naverMap
        }
    }

    private fun onMapTouch() {
        // 지도 전체를 덮어씌우도록 TouchableWrapper를 추가
        val frameLayout = TouchableWrapper(requireActivity(), null, 0, listener)
        frameLayout.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                android.R.color.transparent,
            ),
        )
        (binding.root as ViewGroup).addView(
            frameLayout,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            ),
        )
    }
}
