package org.cazait.cazait_android.ui.view.cafelist.info.inner

import android.content.Context
import android.util.Log
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
import org.cazait.cazait_android.ui.viewmodel.CafeInfoViewModel

@AndroidEntryPoint
class CafeLocTransFragment : BaseFragment<FragmentCafeLocTransBinding, CafeInfoViewModel>(), OnMapReadyCallback {
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
        // MapFragment 객체 초기화, fragment 객체 열기
        val mapFragment = childFragmentManager.findFragmentById(R.id.naver_map) as MapFragment?
            ?: MapFragment.newInstance().also{
                childFragmentManager.beginTransaction().add(R.id.naver_map, it).commit()
            }
        // 인터페이스 역할을 하는 NaverMap 객체 얻기
        // Fragment의 getMapAsync()로 OnMapReadyCallback을 등록하면 비동기로 NaverMap 객체를 얻을 수 있다.
        // NaverMap 객체가 준비되면 OnMapReady() 콜백 메소드 호출
        mapFragment.getMapAsync(this)

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

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

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap


        naverMap.locationSource = locationSource
        naverMap.uiSettings.isLocationButtonEnabled = true

        // 지정한 위치로 카메라 이동
        val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.548476, 127.0726703))
            .animate(CameraAnimation.Easing, 1000)
        naverMap.moveCamera(cameraUpdate)

        //지정한 위치에 마커 적용
        Marker().apply {
            position = LatLng(37.548476, 127.0726703)
            map = naverMap
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        Log.d("CafeLocTransFrag", "onRequestPermissinoResult 권한 요청")
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated) {
                Log.d("CafeLocTransFrag", "onRequestPermissinoResult 권한 거부")
                naverMap.locationTrackingMode = LocationTrackingMode.None
            } else {
                Log.d("CafeLocTransFrag", "onRequestPermissinoResult 권한 허용")
                naverMap.locationTrackingMode = LocationTrackingMode.Follow
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}