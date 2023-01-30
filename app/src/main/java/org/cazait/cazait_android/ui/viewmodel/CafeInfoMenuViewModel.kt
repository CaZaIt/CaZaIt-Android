package org.cazait.cazait_android.ui.viewmodel

<<<<<<< HEAD
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.cazait.cazait_android.data.model.CafeMenu
import org.cazait.cazait_android.data.repository.DataRepositorySource
=======
import dagger.hilt.android.lifecycle.HiltViewModel
>>>>>>> 8c42482 ([Feat] #33 - LiveData + DataBinding + RecyclerView)
import org.cazait.cazait_android.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
open class CafeInfoMenuViewModel @Inject constructor(private val dataRepository: DataRepositorySource) :
    BaseViewModel() {

    private val list = arrayListOf<CafeMenu>()
    private val _cafeMenuList = MutableLiveData<ArrayList<CafeMenu>>()
    val cafeMenuList: LiveData<ArrayList<CafeMenu>>
        get() = _cafeMenuList

    init {
        initDataSet()
    }

<<<<<<< HEAD
    private fun initDataSet() {
        // 테스트용으로 값을 넣어주고 있음
        val dataList = arrayListOf(
            CafeMenu(
                "아메리카노",
                1500,
                "우리나라 원두를 사용하여 더욱 달달한 풍미가 살아있는 최고급 아메리카노"
            ),
            CafeMenu(
                "카페라떼",
                2000,
                "우리나라 원두를 사용하여 더욱 달달한 풍미가 살아있는 최고급 아메리카노"
            ),
            CafeMenu(
                "카페모카",
                3500,
                "우리나라 원두를 사용하여 더욱 달달한 풍미가 살아있는 최고급 아메리카노"
            ),
            CafeMenu(
                "바닐라라떼",
                3000,
                "우리나라 원두를 사용하여 더욱 달달한 풍미가 살아있는 최고급 아메리카노"
            ),
            CafeMenu(
                "카라멜 마끼아또",
                4000,
                "우리나라 원두를 사용하여 더욱 달달한 풍미가 살아있는 최고급 아메리카노"
            ),
            CafeMenu(
                "카라멜 마끼아또",
                4000,
                "우리나라 원두를 사용하여 더욱 달달한 풍미가 살아있는 최고급 아메리카노"
            ),
            CafeMenu(
                "카라멜 마끼아또",
                4000,
                "우리나라 원두를 사용하여 더욱 달달한 풍미가 살아있는 최고급 아메리카노"
            ),
            CafeMenu(
                "카라멜 마끼아또",
                4000,
                "우리나라 원두를 사용하여 더욱 달달한 풍미가 살아있는 최고급 아메리카노"
            )
=======
    fun getMenuPrice(): String {
        return menuPrice
    }
}
class CafeInfoMenuViewModel : BaseViewModel() {
    var menuList: ArrayList<MenuData> = arrayListOf(
        MenuData(
//            "${R.drawable.cafeimg1}",
            "아메리카노",
            "1500원"
        ),
        MenuData(
//            "${R.drawable.cafeimg1}",
            "카페라떼",
            "2000원"
        ),
        MenuData(
//            "${R.drawable.cafeimg1}",
            "카페모카",
            "3500원"
        ),
        MenuData(
//            "${R.drawable.cafeimg1}",
            "바닐라라떼",
            "3000원"
        ),
        MenuData(
//            "${R.drawable.cafeimg1}",
            "카라멜 마끼아또",
            "4000원"
        ),
        MenuData(
//            "${R.drawable.cafeimg1}",
            "카라멜 마끼아또",
            "4000원"
        ),
        MenuData(
//            "${R.drawable.cafeimg1}",
            "카라멜 마끼아또",
            "4000원"
        ),
        MenuData(
//            "${R.drawable.cafeimg1}",
            "카라멜 마끼아또",
            "4000원"
>>>>>>> 8c42482 ([Feat] #33 - LiveData + DataBinding + RecyclerView)
        )
        setCafeMenuList(dataList)
    }

    private fun setCafeMenuList(dataset: ArrayList<CafeMenu>) {
        list.addAll(dataset)
        _cafeMenuList.postValue(dataset)
    }
}