package org.cazait.cazait_android.ui.viewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.cazait.cazait_android.data.model.Cafe
import org.cazait.cazait_android.data.model.CafeImageRes
import org.cazait.cazait_android.data.repository.DataRepository
import org.cazait.cazait_android.data.repository.DataRepositoryImpl
import org.cazait.cazait_android.ui.base.BaseViewModel
import org.cazait.cazait_android.ui.view.cafelist.info.inner.CafeMenuFragment
import javax.inject.Inject

@HiltViewModel
class CafeInfoViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : BaseViewModel() {

    private val cafeImgList = arrayListOf<CafeImageRes>()

    fun makeBundle(bundle: Bundle, cafe: Cafe): Bundle {
        bundle.putLong("cafeId", cafe.id)
        bundle.putString("cafeLat", cafe.latitude)
        bundle.putString("cafeLong", cafe.longitude)
        return bundle
    }

    fun makeCafeImgList(cafe: Cafe): ArrayList<CafeImageRes> {
        cafeImgList.addAll(cafe.cafeImageRes)
        return cafeImgList
    }

    fun cafeName(cafe: Cafe): String {
        return cafe.name
    }

    fun cafeAddress(cafe: Cafe): String {
        return cafe.address
    }

    fun dataTrans(cafe:Cafe){
        val bundle = Bundle()
        bundle.putLong("cafeId", cafe.id)
        bundle.putString("cafeLat", cafe.latitude)
        bundle.putString("cafeLong", cafe.longitude)
        CafeMenuFragment().arguments = bundle
    }
}