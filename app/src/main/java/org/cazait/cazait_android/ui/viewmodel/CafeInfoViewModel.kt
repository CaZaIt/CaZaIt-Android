package org.cazait.cazait_android.ui.viewmodel

import android.os.Bundle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.cazait.cazait_android.data.model.Cafe
import org.cazait.cazait_android.data.model.CafeImageRes
import org.cazait.cazait_android.data.repository.DataRepository
import org.cazait.cazait_android.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CafeInfoViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : BaseViewModel() {

    val cafeImgList = arrayListOf<CafeImageRes>()
    lateinit var bundle: Bundle
    var cafeId: Long = 0
    lateinit var name: String
    lateinit var address: String

    fun makeCafeImgList(cafe: Cafe): ArrayList<CafeImageRes> {
        cafeImgList.addAll(cafe.cafeImageRes)
        return cafeImgList
    }

    fun setCafe(cafe: Cafe) {
        bundle.putLong("cafeId", cafe.id)
        bundle.putString("cafeLat", cafe.latitude)
        bundle.putString("cafeLong", cafe.longitude)
        cafeId = cafe.id
        name = cafe.name
        address = cafe.address
    }
//    companion object {
//        private lateinit var cafe: Cafe
//    }

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
}