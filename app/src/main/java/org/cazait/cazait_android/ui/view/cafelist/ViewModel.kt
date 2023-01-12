package org.cazait.cazait_android.ui.view.cafelist

import androidx.lifecycle.ViewModel
class CafeData(
//    private var cafeImg: View? = null,
    private val cafeName: String,
    private val dis: String,
    private val add: String,
    private val cong: String
) {
//    fun getCafeImg(): View? {
//        return cafeImg
//    }
    fun getCafeName(): String {
        return cafeName
    }

    fun getDistance(): String {
        return dis
    }

    fun getAddress(): String {
        return add
    }

    fun getCongestion(): String {
        return cong
    }
}

class ViewModel:ViewModel() {
    var dataList: ArrayList<CafeData> = arrayListOf(
        CafeData(
//            "${R.drawable.cafeimg1}",
            "롬곡",
            "220m",
            "서울특별시 광진구 광나루로 375-1 2층(군자동)",
            "보통"
        ),
        CafeData(
//            "${R.drawable.cafeimg1}",
            "제주몰빵",
            "1500m",
            "서울특별시 광진구 광나루로 375-1 2층(군자동)",
            "혼잡"
        ),
        CafeData(
//            "${R.drawable.cafeimg1}",
            "팬도로시",
            "2000m",
            "서울특별시 광진구 광나루로 375-1 2층(군자동)",
            "여유"
        )
    )
}