package org.cazait.cazait_android.ui.viewmodel

import android.os.Build
import android.os.Bundle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.cazait.cazait_android.CAFE_ITEM_KEY
import org.cazait.cazait_android.data.model.Cafe
import org.cazait.cazait_android.data.model.CafeImageRes
import org.cazait.cazait_android.data.repository.DataRepository
import org.cazait.cazait_android.ui.base.BaseViewModel
import org.cazait.cazait_android.ui.view.cafelist.info.CafeInformationActivity
import javax.inject.Inject

@HiltViewModel
class CafeInfoViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : BaseViewModel() {

    val cafeImgList = arrayListOf<CafeImageRes>()
    lateinit var bundle: Bundle
    lateinit var name: String
    lateinit var address: String
    val cafe: Cafe? = if (Build.VERSION.SDK_INT >= 33) {
        CafeInformationActivity().intent.getParcelableExtra(CAFE_ITEM_KEY, Cafe::class.java)
    } else {
        CafeInformationActivity().intent.getParcelableExtra(CAFE_ITEM_KEY)
    }

//    fun makeBundle(bundle: Bundle, cafe: Cafe): Bundle {
//        bundle.putLong("cafeId", cafe.id)
//        bundle.putString("cafeLat", cafe.latitude)
//        bundle.putString("cafeLong", cafe.longitude)
//        return bundle
//    }

    //    fun dataTrans(cafe: Cafe) {
//        val bundle = Bundle()
//        bundle.putLong("cafeId", cafe.id)
//        bundle.putString("cafeLat", cafe.latitude)
//        bundle.putString("cafeLong", cafe.longitude)
//        CafeMenuFragment().arguments = bundle
//    }
//    fun makeCafeImgList(cafe: Cafe): ArrayList<CafeImageRes> {
//        cafeImgList.addAll(cafe.cafeImageRes)
//        return cafeImgList
//    }

    fun makeCafeImgList(cafe: Cafe): ArrayList<CafeImageRes>{
        cafeImgList.addAll(cafe.cafeImageRes)
        return cafeImgList
    }

    fun getIntentData() {
//        val cafe = if (Build.VERSION.SDK_INT >= 33) {
//            cafeInformationActivity.intent.getParcelableExtra(CAFE_ITEM_KEY, Cafe::class.java)
//        } else {
//            cafeInformationActivity.intent.getParcelableExtra(CAFE_ITEM_KEY)
//        }

        if (cafe != null) {
            bundle.putLong("cafeId", cafe.id)
            bundle.putString("cafeLat", cafe.latitude)
            bundle.putString("cafeLong", cafe.longitude)
            name = cafe.name
            address = cafe.address
        }
    }
}