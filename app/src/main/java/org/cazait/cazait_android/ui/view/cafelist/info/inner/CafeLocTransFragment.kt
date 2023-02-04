package org.cazait.cazait_android.ui.view.cafelist.info.inner

import android.Manifest
import android.content.Context
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import dagger.hilt.android.AndroidEntryPoint
import org.cazait.cazait_android.R
import org.cazait.cazait_android.databinding.FragmentCafeLocTransBinding
import org.cazait.cazait_android.ui.base.BaseFragment
import org.cazait.cazait_android.ui.view.cafelist.info.util.OnMapTouchListener
import org.cazait.cazait_android.ui.view.cafelist.info.util.TouchableWrapper
import org.cazait.cazait_android.ui.viewmodel.CafeInfoViewModel

@AndroidEntryPoint
class CafeLocTransFragment : BaseFragment<FragmentCafeLocTransBinding, CafeInfoViewModel>(),
    OnMapReadyCallback {
    override val layoutResourceId: Int
        get() = R.layout.fragment_cafe_loc_trans

    override val viewModel: CafeInfoViewModel by viewModels()
    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource
    private var listener: OnMapTouchListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = requireActivity() as? OnMapTouchListener
    }

    override fun initAfterBinding() {
    }

    override fun initBeforeBinding() {
    }

    override fun initView() {
        createMap()
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        onMapTouch()
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap

        naverMap.locationSource = locationSource
        naverMap.uiSettings.isLocationButtonEnabled = true

        showLocationRange()
    }

    private fun createMap() {
        // MapFragment 객체 초기화, fragment 객체 열기
        val mapFragment = childFragmentManager.findFragmentById(R.id.naver_map) as MapFragment?
            ?: MapFragment.newInstance().also {
                childFragmentManager.beginTransaction().add(R.id.naver_map, it).commit()
            }
        // 인터페이스 역할을 하는 NaverMap 객체 얻기
        // Fragment의 getMapAsync()로 OnMapReadyCallback을 등록하면 비동기로 NaverMap 객체를 얻을 수 있다.
        // NaverMap 객체가 준비되면 OnMapReady() 콜백 메소드 호출
        mapFragment.getMapAsync(this)
    }

    private fun showLocationRange() {
        val locationLat = 37.548476
        val locationLng = 127.0726703
        // 지정한 위치로 카메라 이동
        val cameraUpdate = CameraUpdate.scrollTo(LatLng(locationLat, locationLng))
            .animate(CameraAnimation.Easing, 1000)
        naverMap.moveCamera(cameraUpdate)

        //지정한 위치에 마커 적용
        Marker().apply {
            position = LatLng(locationLat, locationLng)
            map = naverMap
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
        private val PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

    private fun onMapTouch() {
        // 지도 전체를 덮어씌우도록 TouchableWrapper를 추가
        val frameLayout = TouchableWrapper(requireActivity(), null, 0, listener)
        frameLayout.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                android.R.color.transparent
            )
        )
        (binding.root as ViewGroup).addView(
            frameLayout,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
    }
}